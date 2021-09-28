package org.konverter.Controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.konverter.Util.REQUEST_TYPE;
import org.konverter.objects.KonverterObject;
import org.konverter.objects.KonverterPdf;
import org.konverter.orchestration.Orchestrator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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

        Future<byte []> future = Orchestrator.executeRequest(files, REQUEST_TYPE.PDF);

        try {
            byte[] toReturn = Objects.requireNonNull(future).get(2, TimeUnit.SECONDS);

            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            String.format("attachment; filename=\"%s.pdf\"", "ConvertedPdf"))
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(toReturn);

        } catch (InterruptedException | ExecutionException | TimeoutException e) {

            logger.info("There was an issue while converting. {}", e.getMessage());
            return ResponseEntity.badRequest().body(null);

        }


    }

}
