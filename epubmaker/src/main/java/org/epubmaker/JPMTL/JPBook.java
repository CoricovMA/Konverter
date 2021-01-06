package org.epubmaker.JPMTL;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Metadata;
import org.epubmaker.Response.BookResponses.EMBook;

import java.util.List;

public class JPBook extends EMBook{

    private List<JPVolume> volumes;

    private String title;

    public JPBook(){};

    public JPBook(String title){
        this.title = title;
    }

    @Override
    public Book getBook() {
        return this.book;
    }

    @Override
    public void generateBook() {
        book = new Book();
        Metadata metadata = new Metadata();
        metadata.addTitle(this.title);
        volumes.forEach(
                jpVolume -> book.addSection(String.format("Volume %s", jpVolume.getVolumeIndex()),
                        jpVolume.getChaptersAsResource().getResource())
        );

    }

    @Override
    protected void generateBook(int upTo) {

    }

    @Override
    protected void generateBook(int from, int to) {

    }

    public void setTitle(String title){
        this.title = title;
    }

}
