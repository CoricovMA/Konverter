package org.konverter.Pdf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class PDFImagePage {

    private static final Logger logger = LogManager.getLogger(PDFImagePage.class);

    private int distanceW = 0;
    private int distanceH = 0;

    private BufferedImage bufferedImage;
    private final float MAX_WIDTH = PDRectangle.A4.getWidth();
    private final float MAX_HEIGHT = PDRectangle.A4.getHeight();

    private boolean shouldResize;

    public PDFImagePage(BufferedImage image) {
        this.bufferedImage = image;
        this.shouldResize =  (image.getHeight() > PDRectangle.A4.getHeight())
                || (image.getWidth() > PDRectangle.A4.getWidth());
    }

    public PDFImagePage(byte[] bytes) throws IOException {
        this.bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
    }

    public BufferedImage getImage() {
        return this.bufferedImage;
    }

    public void rotateLeft() {

    }

    public void rotateRight() {

    }

    public void resize() {
        if (this.shouldResize) {
            this.doResize();
        }
    }

    private void doResize(){
        int targetW = (int) (this.getScale() * bufferedImage.getWidth());
        int targetH = (int) (this.getScale() * bufferedImage.getHeight());
        this.distanceW = (int) Math.floor(Math.abs((targetW - PDRectangle.A4.getWidth()) / 2));
        this.distanceH = (int) Math.floor(Math.abs((targetH - PDRectangle.A4.getHeight()) / 2));

        Image tmp = bufferedImage.getScaledInstance(targetW, targetH, Image.SCALE_SMOOTH);

        BufferedImage resized = new BufferedImage(targetW, targetH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = resized.createGraphics();
        graphics2D.drawImage(tmp, 0, 0, null);
        graphics2D.dispose();

        this.bufferedImage = resized;
    }

    private double getScale() {
        if (this.shouldResize) {
            return Math.min(
                    MAX_HEIGHT / this.bufferedImage.getHeight(),
                    MAX_WIDTH / this.bufferedImage.getWidth()
            );
        } else {
            return 1.0;
        }
    }

    public float width(){
        return this.bufferedImage.getWidth();
    }

    public float height(){
        return this.bufferedImage.getHeight();
    }

    public int getDistanceW() {
        return distanceW;
    }

    public int getDistanceH() {
        return distanceH;
    }
}
