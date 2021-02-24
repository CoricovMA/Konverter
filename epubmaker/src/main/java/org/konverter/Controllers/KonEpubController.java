package org.konverter.Controllers;

import org.konverter.objects.KonBook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class KonEpubController {

    @RequestMapping(
            value = "/convert/epub",
            method = RequestMethod.POST,
            produces = "application/epub+zip"
    )
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<byte []> handleEpubUpload(KonBook generatedBook){

        generatedBook.make();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        String.format("attachment; filename=\"%s.epub\"", generatedBook.getBookTitle()))
                .body(generatedBook.getFinalBook());
    }
}
