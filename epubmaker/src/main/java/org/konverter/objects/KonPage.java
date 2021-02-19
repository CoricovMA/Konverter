package org.konverter.objects;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class KonPage {

    private static final Logger logger = LogManager.getLogger();

    private final Document document;
    private Image image;

    public KonPage(byte [] bytes, Document document) throws IOException, BadElementException {
        this.image = Image.getInstance(bytes);
        this.image.setAlignment(Element.ALIGN_CENTER);
        this.document = document;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public Document getDocument() {
        return document;
    }
    public void resize(){
        this.image.scaleToFit(document.getPageSize());
    }
}
