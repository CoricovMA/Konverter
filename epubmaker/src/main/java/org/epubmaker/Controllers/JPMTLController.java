package org.epubmaker.Controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class JPMTLController {

    @RequestMapping(
            value = "/{book}/{bookID}",
            method = RequestMethod.GET,
            produces = "application/epub+zip"
    )
    public ResponseEntity<byte []> handleGetBook(
                    @RequestParam("book") String book,
                    @RequestParam("bookID") long id
            ){


        return null;
    }


}
