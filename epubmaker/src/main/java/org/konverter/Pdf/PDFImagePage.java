package org.konverter.Pdf;

import org.apache.pdfbox.pdmodel.common.PDRectangle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class PDFImagePage {

    private BufferedImage bufferedImage;
    private static final float MAX_WIDTH = PDRectangle.A4.getWidth();
    private static final float MAX_HEIGHT = PDRectangle.A4.getHeight();

    public PDFImagePage(BufferedImage image){
        this.bufferedImage = image;
    }

    public PDFImagePage(byte[] bytes) throws IOException {
        this.bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
    }

    public BufferedImage getImage(){
        return this.bufferedImage;
    }

    public void rotateLeft(){

    }

    public void rotateRight(){

    }

    public void resize(){

    }

    private boolean shouldResize(){
        return (this.bufferedImage.getHeight() > MAX_HEIGHT)
                &&  (this.bufferedImage.getWidth() > MAX_WIDTH);
    }

}
