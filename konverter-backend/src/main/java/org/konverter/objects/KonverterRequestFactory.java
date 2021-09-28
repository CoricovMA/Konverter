package org.konverter.objects;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class KonverterRequestFactory {

    private static class KonverterEbookRequest implements KonverterRequest {

        private final List<MultipartFile> fileList;

        KonverterEbookRequest(List<MultipartFile> fileList){
            this.fileList = fileList;
        }

        @Override
        public byte[] call() throws Exception {
            KonverterObject obj = new KonverterEbook();
            obj.convert(this.fileList);
            return obj.getConvertedObjectAsBytes();
        }
    }

    private static class KonverterPdfRequest implements KonverterRequest{

        private final List<MultipartFile> fileList;

        KonverterPdfRequest(List<MultipartFile> fileList){
            this.fileList = fileList;
        }

        @Override
        public byte[] call() throws Exception {
            KonverterObject pdfObject = new KonverterPdf();
            pdfObject.convert(this.fileList);
            return pdfObject.getConvertedObjectAsBytes();
        }
    }

    public static KonverterRequest newEbookRequest(List<MultipartFile> fileList){
        return new KonverterEbookRequest(fileList);
    }

    public static KonverterRequest newPdfRequest(List<MultipartFile> fileList){
        return new KonverterPdfRequest(fileList);
    }

}
