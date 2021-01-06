package org.epubmaker.Response.BookResponses;

import nl.siegmann.epublib.domain.Book;

public abstract class EMBook {

    protected Book book;

    public abstract Book getBook();

    protected abstract void generateBook();

    protected abstract void generateBook(int upTo);

    protected abstract void generateBook(int from, int to);
}
