package org.konverter.Pdf;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompiledPDF {

    private static final Logger logger = LogManager.getLogger(CompiledPDF.class);
    private final List<PDFImagePage> imageList;
    private final Document document;
    private final ByteArrayOutputStream outputStream;
    private final PdfWriter writer;

    public CompiledPDF() throws DocumentException {
        this.imageList = new ArrayList<>();
        this.document = new Document();
        this.outputStream = new ByteArrayOutputStream();
        this.writer = PdfWriter.getInstance(this.document, this.outputStream);
    }

    public void addImage(byte[] imageBytes) throws IOException, BadElementException {
        imageList.add(new PDFImagePage(imageBytes, document));
    }

    public void generatePdf(){
        compilePDF();
        document.close();
    }

    private void compilePDF() {
        resizePages();
        addPagesToPdf();
    }

    public byte[] getFinalPdfBytes() {
        return outputStream.toByteArray();
    }

    private void resizePages() {
        logger.info("Resizing pages.");
        long start = System.currentTimeMillis();

        imageList.stream().parallel().forEach(
                PDFImagePage::resize
        );

        logger.info("Resizing completed. {}ms.", (System.currentTimeMillis() - start));
    }

    private void addPagesToPdf() {
        logger.info("Adding pages to pdf.");
        long start = System.currentTimeMillis();
        this.document.open();
        imageList.forEach(image -> {
            document.newPage();
            try {
                document.add(image.getImage());
            } catch (DocumentException e) {
                logger.warn("Error while adding image to document. {}", e.getMessage());
            }
        });

        logger.info("Pages added. Action took {}ms.", (System.currentTimeMillis() - start));

    }
}
