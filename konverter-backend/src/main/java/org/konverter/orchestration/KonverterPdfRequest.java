package org.konverter.orchestration;

import org.konverter.pdf.KonverterPdfDocument;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.Callable;

public class KonverterPdfRequest implements KonverterRequest {

    private final KonverterPdfDocument document;

    public KonverterPdfRequest(KonverterPdfDocument document){
        this.document = document;
    }


    @Override
    public byte[] call() throws Exception {
        return new byte[0];
    }
}
