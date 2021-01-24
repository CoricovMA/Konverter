package org.konverter.Controllers;

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
import java.util.List;

@Controller
public class ToPdfController {

    @RequestMapping(
            value = "/convert/pdf",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    @ResponseBody
    public ResponseEntity<byte[]> handleFileUpload(@RequestParam("files") List<MultipartFile> files) {
        try {
            for(MultipartFile file: files){
                PDFObj obj = new PDFObj(file.getBytes());
                return ResponseEntity
                        .ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s.pdf\"",
                                file.getOriginalFilename().split("\\.")[0]))
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(obj.getPdfBytes());
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

}
