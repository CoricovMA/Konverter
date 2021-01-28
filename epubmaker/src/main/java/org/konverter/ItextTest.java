package org.konverter;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;

import javax.imageio.ImageIO;
import javax.print.Doc;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;

public class ItextTest {

    public static void main(String [] args){
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            FileOutputStream fos = new FileOutputStream("test.pdf");
            Document doc = new Document();
            PdfWriter writer = PdfWriter.getInstance(doc, fos);
            doc.open();
            Image image = Image.getInstance("test2.png");
            image.setAlignment(Element.ALIGN_CENTER);
            System.out.println(image.isScaleToFitHeight());
            System.out.println(image.getHeight());
            System.out.println(image.getWidth());
            System.out.println(image.getScaledWidth());
            System.out.println(doc.getPageSize());
            image.scaleToFit(doc.getPageSize());
            doc.add(image);
            doc.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
//        try {
//            FileOutputStream fp = new FileOutputStream("test.pdf");
//            BufferedImage image = ImageIO.read(new File("test.jpg"));
//            ImageIO.write(image, "jpg", os);
//            ImageData data = ImageDataFactory.create(os.toByteArray());
//            PdfDocument pdfDoc = new PdfDocument();
//            PdfWriter writer = PdfWriter.getInstance(pdfDoc, fp);
//
//            pdfDoc.open();
//            pdfDoc.newPage();
//            pdfDoc.add((Element) new Image(data));
//            pdfDoc.close();
//        } catch (IOException | DocumentException e) {
//            e.printStackTrace();
//        }

    }

}
