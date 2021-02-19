package org.konverter.Controllers;

import com.itextpdf.text.DocumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.konverter.objects.KonPdf;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RestController
public class KonPdfController {

    private static final Logger logger = LogManager.getLogger();

    @RequestMapping(
            value = "/convert/pdf",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<byte []> handlePdfUpload( KonPdf pdf){

        byte [] toReturn = {};

        try {
            pdf.init();
            pdf.generateImages();
            pdf.resizeImages();
            pdf.addPagesToPdf();
            toReturn = pdf.build();
        } catch (DocumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(toReturn);
        }

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        String.format("attachment; filename=\"%s.pdf\"", pdf.getTitle()))
                .contentType(MediaType.APPLICATION_PDF)
                .body(toReturn);

    }

}
