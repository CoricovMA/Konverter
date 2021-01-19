package org.epubmaker.Basic;

import nl.siegmann.epublib.domain.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epubmaker.Response.BookResponses.EMBook;
import org.epubmaker.Util.HtmlMaker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BasicBook extends EMBook {

    private static final Logger logger = LogManager.getLogger(BasicBook.class);
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    private final String delimiter;
    private final byte[] receivedBytes;
    private String bookTitle;

    private String receivedBook;
    private String finalBook;

    private Book book;
    private boolean hasDelimiter;

    public BasicBook(byte [] bytes) throws IOException {
        this(bytes, "");
    }

    public BasicBook(byte[] bytes, String delimiter) throws IOException {
        super();
        this.delimiter = delimiter;
        this.receivedBytes = bytes;
        this.receivedBook = new String(bytes).strip().trim();
        this.hasDelimiter = delimiter.length() > 0;
        generateBook();
        writeEbook();
    }

    private void writeEbook() {
        /**
         * write ebook
         */
    }

    private void generateBook() throws IOException {

        logger.info("Generating book.");

        long start = System.currentTimeMillis();

        if(hasDelimiter){
            bookWithDelimiter();
        }else{
            bookNoDelimiter();
        }

        logger.info("Book generated successfully in {}ms", (System.currentTimeMillis() - start));

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
        return this.finalBook.getBytes();
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
