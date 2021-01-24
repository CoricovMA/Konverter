package org.konverter.Pdf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PDFObj {

    private static final Logger logger = LogManager.getLogger(PDFObj.class);
    private final byte [] pictureBytes;
    private byte [] pdfBytes;
    private String pictureFormat;
    private String pictureName;
    private PDDocument pdfToReturn;

    private final int MAX_PAGE_HEIGHT = (int)PDRectangle.A4.getHeight();
    private final int MAX_PAGE_WIDTH = (int)PDRectangle.A4.getHeight();

    public PDFObj(byte [] givenBytes) throws IOException {
        this(givenBytes, "test", "jpg");
    }

    public PDFObj(byte [] givenBytes, String format, String name) throws IOException {
        this.pictureBytes = givenBytes;
        this.pictureFormat = format;
        this.pictureName = name;
        generatePDF();
    }

    private void generatePDF() throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(this.pictureBytes);
        BufferedImage image = ImageIO.read((inputStream));
//        image = getResizedImage(image, image.getHeight(), image.getWidth());

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        PDImageXObject imgObj = JPEGFactory.createFromImage(document, image);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        contentStream.drawImage(imgObj, 0, 0, PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight());
        contentStream.close();
        document.save(outputStream);
        document.close();

        this.pdfBytes = outputStream.toByteArray();
    }

    private BufferedImage getResizedImage(BufferedImage img, int originalHeight, int originalWidth){
//        PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight();
        double scale = getScale(originalWidth, (int) PDRectangle.A4.getWidth());
        int targetHeight = (int) (scale * originalHeight);
        int targetWidth = (int) (scale * originalHeight);
        BufferedImage resized = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resized.createGraphics();
        graphics2D.drawImage(img, 0, 0, originalWidth, targetHeight, null);
        graphics2D.dispose();
        return resized;
    }

    private double getScale(int original, int max){
        if(original < max){
            return 1.0;
        }

        return (double) original/max;

    }

    public byte[] getPdfBytes(){
        return this.pdfBytes;
    }

}
