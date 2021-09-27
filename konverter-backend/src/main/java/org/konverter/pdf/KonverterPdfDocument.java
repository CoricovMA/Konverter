package org.konverter.pdf;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KonverterPdfDocument {

    private static final Logger logger = LogManager.getLogger(KonverterPdfDocument.class);

    private List<MultipartFile> files;
    private List<KonverterPdfPage> pageList = new ArrayList<>();
    private Document document = new Document();
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private PdfWriter writer;
    private String title;

    public KonverterPdfDocument(){}

    public void init() throws DocumentException {
        logger.debug("KonPdf init");
        this.writer = PdfWriter.getInstance(this.document, this.outputStream);
        this.title = files.get(0).getName().split("\\.")[0];
    }

    public void generateImages(){
        logger.info("Generating images.");

        long start = System.currentTimeMillis();
        files.forEach(
                file ->{
                    try {
                        pageList.add(new KonverterPdfPage(file.getBytes(), this.document));
                    } catch (IOException | BadElementException e) {
                        logger.warn("There was an error generating a page. {}", e.getMessage());
                    }
                }
        );

        logger.info("Images Generated. Action took {}ms.", (System.currentTimeMillis() - start));

    }

    public void resizeImages(){
        logger.info("Resizing images.");

        long start = System.currentTimeMillis();
        pageList.stream().parallel().forEach(
                KonverterPdfPage::resize
        );

        logger.info("Images Added. Action took {}ms.", (System.currentTimeMillis() - start));
    }

    public void addPagesToPdfDocument(){
        logger.info("Adding pages to pdf.");

        long start = System.currentTimeMillis();
        this.document.open();

        pageList.forEach(
                image ->{
                    document.newPage();
                    try {
                        document.add(image.getImage());
                    } catch (DocumentException e) {
                        logger.warn("Error while adding image to document. {}", e.getMessage());
                    }
                }
        );

        logger.info("Pages added. Action took {}ms.", (System.currentTimeMillis() - start));

    }

    public byte[] getPdfDocumentAsBytes(){
        document.close();
        return this.outputStream.toByteArray();
    }

    public String getTitle(){
        return this.title;
    }

    public List<KonverterPdfPage> getPageList() {
        return pageList;
    }

    public void setPageList(List<KonverterPdfPage> pageList) {
        this.pageList = pageList;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public ByteArrayOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(ByteArrayOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public PdfWriter getWriter() {
        return writer;
    }

    public void setWriter(PdfWriter writer) {
        this.writer = writer;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}