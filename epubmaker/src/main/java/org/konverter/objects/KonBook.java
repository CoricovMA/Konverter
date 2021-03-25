package org.konverter.objects;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.konverter.Util.HtmlMaker;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class KonBook {

    private static final Logger logger = LogManager.getLogger(KonBook.class);
    private static final EpubWriter writer = new EpubWriter();
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    private List<MultipartFile> files;

    private String bookTitle;
    private String receivedBook;
    private String finalBook;

    private Book convertedEbook;

    public KonBook(){}

    public void make(){
        ByteArrayOutputStream bos= new ByteArrayOutputStream();

        this.bookTitle = files.get(0).getName().split("\\.")[0];

        for (MultipartFile file : files) {
            try {

                logger.info("Writing {} to ByteStream.", file.getName());

                bos.write(file.getBytes());

            } catch (IOException e) {

                logger.warn("There was an error writing {} to ByteStream. {}", file.getName(), e.getStackTrace());

            }
        }

        this.receivedBook = bos.toString().trim();

        convertBook();
    }

    private void convertBook() {

        logger.info("Generating book");

        long start = System.currentTimeMillis();
        
        generateBook();
        
        writeBook();

        logger.info("Book generation done in {}ms", (System.currentTimeMillis() - start));
    }

    private void generateBook() {
        this.finalBook = HtmlMaker.getTitle(this.bookTitle) +
                HtmlMaker.prettyParagraph(receivedBook);
    }

    private void writeBook() {

        logger.info("Writing book.");

        this.convertedEbook = new Book();
        Metadata metadata = new Metadata();
        metadata.addTitle(this.bookTitle);
        convertedEbook.addSection(this.bookTitle, new Resource(this.finalBook.getBytes(), MediatypeService.XHTML));

        try {

            writer.write(this.convertedEbook, outputStream);

        } catch (IOException e) {

            logger.warn("There was an error writing the book. {}", e.getStackTrace());

        }

    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setFiles(List<MultipartFile> files){
        this.files = files;
    }

    public byte [] getFinalBook(){
        return this.outputStream.toByteArray();
    }

    public String getBookTitle() {
        return bookTitle;
    }
}
