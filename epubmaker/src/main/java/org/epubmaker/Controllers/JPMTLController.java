package org.epubmaker.Controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epubmaker.JPMTL.JPBook;
import org.epubmaker.Request.RequestMaker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class JPMTLController {

    private static final Logger logger = LogManager.getLogger(JPMTLController.class);

    @RequestMapping(
            value = "/{bookTitle}/{bookID}",
            method = RequestMethod.GET,
            produces = "application/epub+zip"
    )
    public void handleGetBook(
            HttpServletResponse response,
            @PathVariable String bookTitle, @PathVariable String bookID){


        try {
            logger.info("Getting book {}. With id: {}", bookTitle, bookID);

            long start = System.currentTimeMillis();

            JPBook book = RequestMaker.getChapters(bookID, bookTitle);
            book.writeBook();

            logger.info("Book retrieved in {}ms", (System.currentTimeMillis() - start));

            response.setHeader( "Content-Disposition", String.format("attachment;filename=%s.epub",  bookTitle ));
            response.setContentType("application/epub+zip");
            response.getOutputStream().write(book.getOutputStream().toByteArray());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException ioException) {
            logger.warn("There was an error creating the book. {}", ioException.getMessage());
        }

    }


}
