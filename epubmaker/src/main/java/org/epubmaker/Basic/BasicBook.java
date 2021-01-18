package org.epubmaker.Basic;

import nl.siegmann.epublib.domain.Book;
import org.epubmaker.Response.BookResponses.EMBook;

import java.io.ByteArrayOutputStream;

public class BasicBook extends EMBook {

    private final String delimiter;
    private final byte[] receivedBytes;
    private String bookTitle;

    private String receivedBook;
    private Book book;
    private boolean hasDelimiter;
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    private String finalBook;

    public BasicBook(byte [] bytes){
        this(bytes, "");
    }

    public BasicBook(byte[] bytes, String delimiter) {
        super();
        this.delimiter = delimiter;
        this.receivedBytes = bytes;
        this.receivedBook = new String(bytes);
        this.hasDelimiter = delimiter.length() > 0;
    }

    private void generateBook(){
        String [] separatedChapters;

    }

    private void bookWithDelimiter(){

    }

    private void bookNoDelimiter(){

    }

    @Override
    public byte[] getBookAsBytes() {
        return new byte[0];
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
