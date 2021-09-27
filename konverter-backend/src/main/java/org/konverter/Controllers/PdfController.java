package org.konverter.Controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.konverter.objects.KonverterObject;
import org.konverter.objects.KonverterPdf;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class PdfController {

    private static final Logger logger = LogManager.getLogger(PdfController.class);

    @RequestMapping(
            value = "/convert/pdf",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ResponseEntity<byte []> handlePdfUpload(List<MultipartFile> files){

        logger.info("Received pdf conversion request. {} pages.", files.size());

        KonverterObject pdf = new KonverterPdf();
        pdf.convert(files);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        String.format("attachment; filename=\"%s.pdf\"", "ConvertedPdf"))
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf.getConvertedObjectAsBytes());

    }

}
