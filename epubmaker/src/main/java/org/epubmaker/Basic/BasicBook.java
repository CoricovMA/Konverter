package org.epubmaker.Basic;

import nl.siegmann.epublib.domain.Book;
import org.epubmaker.Response.BookResponses.EMBook;

public class BasicBook extends EMBook {

    private final String delimiter;
    private final byte[] receivedBytes;
    private String receivedBook;
    private Book book;

    public BasicBook(byte [] bytes){
        this(bytes, "");
    }

    public BasicBook(byte[] bytes, String delimiter) {
        super();
        this.delimiter = delimiter;
        this.receivedBytes = bytes;
        this.receivedBook = new String(bytes);
    }

    @Override
    public byte[] getBookAsBytes() {
        return new byte[0];
    }

    @Override
    public String getTitle() {
        return null;
    }
}
