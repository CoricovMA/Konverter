package org.konverter.Controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.konverter.orchestration.Orchestrator;
import org.konverter.Util.REQUEST_TYPE;
import org.springframework.http.HttpHeaders;
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
public class EpubController {

    private static final Logger logger = LogManager.getLogger();

    @RequestMapping(
            value = "/convert/epub",
            method = RequestMethod.POST,
            produces = "application/epub+zip"
    )
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ResponseEntity<byte []> handleEpubUpload(List<MultipartFile> files){

        logger.info("Received ebook conversion request.");

        Future<byte[]> future = Orchestrator.executeRequest(files, REQUEST_TYPE.EBOOK);

        try {
            byte [] toReturn =  Objects.requireNonNull(future).get(2, TimeUnit.SECONDS);

            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            String.format("attachment; filename=\"%s.epub\"", "ConvertedEbook"))
                    .body(toReturn);

        } catch (InterruptedException | ExecutionException | TimeoutException e) {

            logger.warn("Something went wrong while doing the request. {}", e.getMessage());

            return ResponseEntity.badRequest().body(null);

        }


    }
}
