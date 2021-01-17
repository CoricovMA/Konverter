package org.epubmaker.Response.BookResponses;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.epub.EpubWriter;

public abstract class EMBook {

    protected static final EpubWriter writer = new EpubWriter();

    protected Book book;

    public abstract byte[] getBookAsBytes();

    public abstract String getTitle();
}
