package org.konverter.pdf;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class KonverterPdfPage {

    private static final Logger logger = LogManager.getLogger(KonverterPdfPage.class);

    private final Document document;
    private Image image;

    public KonverterPdfPage(byte [] bytes, Document document) throws IOException, BadElementException {
        this.image = Image.getInstance(bytes);
        this.image.setAlignment(Element.ALIGN_CENTER);
        this.document = document;
    }

    public Image getImage() {
        return image;
    }

    public void resize(){
        this.image.scaleToFit(document.getPageSize());
    }
}
