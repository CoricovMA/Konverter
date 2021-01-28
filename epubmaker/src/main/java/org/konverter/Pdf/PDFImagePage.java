package org.konverter.Pdf;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.io.IOException;

public class PDFImagePage {

    private static final Logger logger = LogManager.getLogger(PDFImagePage.class);

    private int distanceW = 0;
    private int distanceH = 0;

    private final Document doc;
    private Image image;
    private final float MAX_WIDTH = PDRectangle.A4.getWidth();
    private final float MAX_HEIGHT = PDRectangle.A4.getHeight();

    private boolean shouldResize;

    public PDFImagePage(byte[] bytes, Document doc) throws IOException, BadElementException {
        this.image = Image.getInstance(bytes);
        this.image.setAlignment(Element.ALIGN_CENTER);
        this.doc = doc;
    }

    public Image getImage() {
        return this.image;
    }

    public void rotateLeft() {

    }

    public void rotateRight() {

    }

    public void resize() {
        image.scaleToFit(doc.getPageSize());
    }

    private void doResize(){

    }

}
