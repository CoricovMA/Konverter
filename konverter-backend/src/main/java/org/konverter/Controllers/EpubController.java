package org.konverter.Controllers;

import org.konverter.epub.KonverterBook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EpubController {

    @RequestMapping(
            value = "/convert/epub",
            method = RequestMethod.POST,
            produces = "application/epub+zip"
    )
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ResponseEntity<byte []> handleEpubUpload(KonverterBook generatedBook){

        generatedBook.convertToEbook();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        String.format("attachment; filename=\"%s.epub\"", generatedBook.getBookTitle()))
                .body(generatedBook.getGeneratedEbookAsBytes());
    }
}
