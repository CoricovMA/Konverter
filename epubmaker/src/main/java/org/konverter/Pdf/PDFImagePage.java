package org.konverter.Pdf;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class PDFImagePage {

    private BufferedImage bufferedImage;

    public PDFImagePage(BufferedImage image){
        this.bufferedImage = image;
    }

    public PDFImagePage(byte[] bytes) throws IOException {
        this.bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
    }
}
