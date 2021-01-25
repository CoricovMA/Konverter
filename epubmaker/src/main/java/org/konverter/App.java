package org.konverter;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.annotation.Documented;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        File f = new File("test2.png");
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(FileUtils.readFileToByteArray(f)));
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            double scale = getScale(image.getWidth(), image.getHeight(), PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight());

            int targetW = (int) (scale * image.getWidth());
            int targetH = (int) (scale * image.getHeight());

            long start = System.currentTimeMillis();

            Image tmp = image.getScaledInstance(targetW, targetH, Image.SCALE_SMOOTH);
            /**
             * resize only if scale not 1
             */
            BufferedImage resized = new BufferedImage(targetW, targetH, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resized.createGraphics();
            g2d.drawImage(tmp, 0, 0,null);
            g2d.dispose();

            contentStream.drawImage(JPEGFactory.createFromImage(document, resized),
                    0, 0,
                    resized.getWidth(),
                    resized.getHeight()
                    );
            contentStream.close();
            document.addPage(page);
            document.save(new File("doc.pdf"));
            document.close();
            System.out.println((System.currentTimeMillis() - start));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //        SpringApplication.run(App.class, args);
    }

    public static double getScale(float originalW, float originalH, float maxW, float maxH){
        if((originalW < maxW) && (originalH < maxH)){
            return 1.0;
        }else{
            return 0;
        }

    }

}
