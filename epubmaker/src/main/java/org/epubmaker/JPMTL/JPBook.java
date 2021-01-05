package org.epubmaker.JPMTL;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Metadata;
import org.epubmaker.Response.EMBook;

import java.sql.Time;
import java.util.List;

public class JPBook extends EMBook{

    @JsonProperty("volumes")
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
                jpVolume -> {
                }
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
