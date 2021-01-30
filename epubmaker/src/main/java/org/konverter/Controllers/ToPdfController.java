package org.konverter.Controllers;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.konverter.Pdf.CompiledPDF;
import org.konverter.Pdf.PDFObj;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class ToPdfController {

    private static final Logger logger = LogManager.getLogger(ToPdfController.class);

    @RequestMapping(
            value = "/convert/pdf",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    @ResponseBody
    public ResponseEntity<byte[]> handleFileUpload(@RequestParam("files") List<MultipartFile> files) {
        logger.info("Received {} files. Converting.", files.size());
        long start = System.currentTimeMillis();
        long startToFinish = System.currentTimeMillis();
        try {

            CompiledPDF pdf = new CompiledPDF().setPages(files.size());

            logger.info("Generating picture pages.");

            for(MultipartFile file: files){
                try {
                    pdf.addImage(file.getBytes());
                }catch (IOException | BadElementException e){
                    logger.warn("Something went adding files to pdf wrong. {}", e.getMessage());
                }
            }

            logger.info("Pictures added to pages. Action took {}ms.", (System.currentTimeMillis() - start));
            start = System.currentTimeMillis();

            logger.info("Generating pdf.");

            pdf.generatePdf();

            logger.info("Pdf Generated. Action took {}ms.", (System.currentTimeMillis() - start));
            logger.info("Total conversion took {}ms.", (System.currentTimeMillis() - startToFinish));

            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s.pdf\"",
                            files.get(0).getOriginalFilename().split("\\.")[0]))
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf.getFinalPdfBytes());
        } catch ( DocumentException e) {
            logger.warn("Something went wrong. {}.", e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body("".getBytes(StandardCharsets.UTF_8));
        }


    }

}
