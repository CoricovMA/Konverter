package org.konverter.objects;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KonverterPdf implements KonverterObject{

    private static final Logger logger = LogManager.getLogger();

    private final List<KonverterPdfPage> pageList = new ArrayList<>();
    private final Document document = new Document();
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
    private PdfWriter writer;

    private static class KonverterPdfPage{

        private final Image image;
        private final Document pdfDocument;

        KonverterPdfPage(byte[] givenImageAsBytes, Document pdfDocument) throws BadElementException, IOException {
            this.image = Image.getInstance(givenImageAsBytes);
            this.pdfDocument = pdfDocument;
        }

        void resize(){
            this.image.setAlignment(Element.ALIGN_CENTER);
            this.image.scaleToFit(this.pdfDocument.getPageSize());
        }

        Image getImage(){
            return this.image;
        }
    }

    @Override
    public void convert(List<MultipartFile> files) {
        this.generateImagesFromGivenFiles(files);
        this.resizeImages();
        this.addResizedImagesToPdfDocument();
        this.writer.close();
    }

    @Override
    public byte[] getConvertedObjectAsBytes() {
        return this.bos.toByteArray();
    }

    private void generateImagesFromGivenFiles(List<MultipartFile> files){
        logger.info("Generating Images.");
        long start = System.nanoTime();

        files.stream().sequential().forEach(
                file ->{
                    try {
                        pageList.add(new KonverterPdfPage(file.getBytes(), this.document));
                    } catch (BadElementException | IOException e) {
                        logger.warn("There was an error generating a PDF page. Execution time {}ns. {}",
                                System.nanoTime() - start, e.getMessage());
                    }
                }
        );

        logger.info("Images generated from given files. Action took {}ns.", (System.nanoTime() - start) );
    }

    private void resizeImages(){
        logger.info("Resizing imaged before appending pdf.");
        long start = System.nanoTime();

        pageList.stream().parallel().forEach(KonverterPdfPage::resize);

        logger.info("Images successfully added. Action took {}ns.", (System.nanoTime() - start));
    }

    private void initDocument() {
        try {
            this.writer = PdfWriter.getInstance(this.document, this.bos);
            this.document.open();
        } catch (DocumentException e) {
            logger.warn("There was an error creating the document. {}", e.getMessage());
        }
    }

    private void addResizedImagesToPdfDocument() {
        initDocument();

        logger.info("Adding pages to PDF.");
        long start = System.nanoTime();

        pageList
                .stream()
                .sequential()
                .forEach(
                        page ->{
                            document.newPage();
                            try {

                                document.add(page.getImage());

                            } catch (DocumentException e) {

                                logger.warn("There was an error while adding a page to a document. {}", e.getMessage());

                            }
                        }
                );

        logger.info("Pages added successfully. Action took {}ns.", (System.nanoTime() - start) );
    }
}
