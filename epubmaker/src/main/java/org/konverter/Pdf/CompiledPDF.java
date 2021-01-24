package org.konverter.Pdf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompiledPDF {

    private static final Logger logger = LogManager.getLogger(CompiledPDF.class);
    private final List<BufferedImage> imageList;
    private final List<PDFImagePage> pages;
    private final PDDocument document;
    private final ByteArrayOutputStream outputStream;
    private byte [] finalPdfBytes;

    public CompiledPDF(){
        this.imageList = new ArrayList<>();
        this.pages = new ArrayList<>();
        this.document = new PDDocument();
        this.outputStream = new ByteArrayOutputStream();
    }

    public void addImage(byte[] imageBytes) throws IOException {
        imageList.add(ImageIO.read(new ByteArrayInputStream(imageBytes)));
    }

    public void generatePdf() throws IOException {
        addPages();
        document.save(outputStream);
        document.close();
        this.finalPdfBytes = outputStream.toByteArray();
    }

    private void addPages() throws IOException {
        for(BufferedImage image: imageList){
            PDPage page = new PDPage();

            this.document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.drawImage(JPEGFactory.createFromImage(document, image),
                    0, 0,
                    PDRectangle.A4.getWidth(),
                    PDRectangle.A4.getHeight());

            contentStream.close();
        }
    }

    public byte[] getFinalPdfBytes() {
        return finalPdfBytes;
    }


}
