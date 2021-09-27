package org.konverter.Controllers;

import org.konverter.objects.KonverterEbook;
import org.konverter.objects.KonverterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class EpubController {

    @RequestMapping(
            value = "/convert/epub",
            method = RequestMethod.POST,
            produces = "application/epub+zip"
    )
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ResponseEntity<byte []> handleEpubUpload(List<MultipartFile> files){

        KonverterObject k = new KonverterEbook();
        k.convert(files);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        String.format("attachment; filename=\"%s.epub\"", "test"))
                .body(k.getConvertedObjectAsBytes());
    }
}
