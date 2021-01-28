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
    private final List<PDFImagePage> pages;
    private final Document document;
    private final ByteArrayOutputStream outputStream;
    private byte [] finalPdfBytes;
    private final PdfWriter writer;

    public CompiledPDF() throws DocumentException {
        this.imageList = new ArrayList<>();
        this.pages = new ArrayList<>();
        this.document = new Document();
        this.outputStream = new ByteArrayOutputStream();
        this.writer = PdfWriter.getInstance(this.document, this.outputStream);
    }

    public void addImage(byte[] imageBytes) throws IOException, BadElementException {
        imageList.add(new PDFImagePage(imageBytes, document));
    }

    public void generatePdf() throws IOException {
//        compilePDF();
//        document.save(outputStream);
//        document.close();
//        this.finalPdfBytes = outputStream.toByteArray();
    }

    private void compilePDF() throws IOException {
        resizePages();
        addPagesToPdf();
    }

    public byte[] getFinalPdfBytes() {
        return finalPdfBytes;
    }

    private void resizePages(){
        imageList.stream().parallel().forEach(
                PDFImagePage::resize
        );
    }
    
    private void addPagesToPdf() throws IOException {

//        for(PDFImagePage image: imageList){
//            PDPage page = new PDPage();
//
//            this.document.addPage(page);
//
//            PDPageContentStream contentStream = new PDPageContentStream(document, page);
//            contentStream.drawImage(JPEGFactory.createFromImage(document, image.getImage()),
//                    0, 0,
//                    image.width(),
//                    image.height());
//
//            contentStream.close();
//        }
    }
}
