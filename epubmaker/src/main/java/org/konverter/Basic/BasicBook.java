package org.konverter.Basic;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.konverter.Response.BookResponses.EMBook;
import org.konverter.Util.HtmlMaker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BasicBook extends EMBook {

    private static final Logger logger = LogManager.getLogger(BasicBook.class);
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private static final EpubWriter writer = new EpubWriter();

    private final String delimiter;
    private String bookTitle;

    private String receivedBook;
    private String finalBook;

    private Book book;
    private boolean hasDelimiter;

    public BasicBook(byte [] bytes) throws IOException {
        this(bytes, "", "RandoBook");
    }

    public BasicBook(byte[] bytes, String delimiter, String title) throws IOException {
        super();
        this.delimiter = delimiter;
        this.bookTitle = title;
        this.receivedBook = new String(bytes).strip().trim();
        this.hasDelimiter = delimiter.length() > 0;
        generateBook();
    }

    private void generateBook() throws IOException {

        logger.info("Generating book.");

        long start = System.currentTimeMillis();

        if(hasDelimiter){
            bookWithDelimiter();
        }else{
            bookNoDelimiter();
        }

        writeEbook();

        logger.info("Book generated successfully in {}ms", (System.currentTimeMillis() - start));

    }

    private void writeEbook() throws IOException {
        this.book = new Book();
        Metadata metadata = new Metadata();
        metadata.addTitle(this.bookTitle);
        book.addSection(this.bookTitle, new Resource(this.finalBook.getBytes(), MediatypeService.XHTML));
        writer.write(this.book, outputStream);
    }

    private void bookWithDelimiter(){
        String [] chapters = this.receivedBook.split(delimiter);
        StringBuilder sb = new StringBuilder();

        for (String chapter : chapters){
            sb.append(HtmlMaker.getParagraph(chapter));
        }

        this.finalBook = sb.toString();
    }

    private void bookNoDelimiter(){
        this.finalBook = HtmlMaker.getTitle(this.bookTitle) +
                HtmlMaker.prettyParagraph(receivedBook);
    }


    @Override
    public byte[] getBookAsBytes() {
        return this.outputStream.toByteArray();
    }

    @Override
    public String getTitle() {
        return null;
    }

    public BasicBook setTitle(String title){
        this.bookTitle = title;
        return this;
    }
}
