package org.epubmaker.Controllers;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epubmaker.Basic.BasicBook;
import org.epubmaker.Response.BookResponses.EMBook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ConvertController {

    private static final Logger logger = LogManager.getLogger(ConvertController.class);

    @RequestMapping(
            value = "/convert",
            method = RequestMethod.POST,
            produces = "application/epub+zip"
    )
    public ResponseEntity<byte []> handleConvertText(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "delimiter", required = false, defaultValue = "") String delimiter)
    {
        if(!FilenameUtils.isExtension(file.getOriginalFilename(), "txt")) {
            logger.info("Received a request with a wrong file format. File: \"{}\".", file.getName());
            return ResponseEntity
                    .badRequest()
                    .body(new byte[]{});
        }

        EMBook book = null;
        long start = System.currentTimeMillis();

        try {
            book = new BasicBook(file.getBytes(),
                    delimiter,
                    file.getName().split("\\.")[0]);

        } catch (IOException ioException) {

            logger.warn("There was an error generating the book. {}", ioException.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }

        logger.info("Successfully generated book from {}. Conversion took: {}ms",
                file.getName(), (System.currentTimeMillis() - start) );

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s.epub\"", book.getTitle()))
                .body(book.getBookAsBytes());

    }


}
