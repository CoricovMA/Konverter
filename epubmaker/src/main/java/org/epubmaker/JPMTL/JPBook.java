package org.epubmaker.JPMTL;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;
import org.epubmaker.Response.BookResponses.EMBook;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class JPBook extends EMBook{

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final EpubWriter writer = new EpubWriter();
    private ByteArrayOutputStream outputStream;

    @JsonProperty("volumes")
    private List<JPVolume> volumes;

    private String title;

    public JPBook(){};

    public JPBook(String title){
        this.title = title;
    }

    public JPBook(ChapterList cl, String bookTitle) {
        this.outputStream = new ByteArrayOutputStream();
        this.book = new Book();
        Metadata metadata = new Metadata();
        metadata.addTitle(bookTitle);
        cl.getChapterInfoList().forEach(
                chapter ->{
                    book.addSection(chapter.getChapter().getTitle()
                            , new Resource(chapter.getChapter().getHtml().getBytes(), MediatypeService.XHTML));
                }
        );
    }

    @Override
    public Book getBook() {
        return this.book;
    }

    @Override
    public void generateBook(long bookID) {
        setVolumes();
        book = new Book();
        Metadata metadata = new Metadata();
        metadata.addTitle(this.title);
        volumes.forEach(
                jpVolume -> book.addSection(String.format("Volume %s", jpVolume.getVolumeIndex()),
                        jpVolume.getChaptersAsResource().getResource())
        );

    }

    private void setVolumes() {

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

    public List<JPVolume> getVolumes(){
        return this.volumes;
    }

    public String string() throws JsonProcessingException {
        JSONObject jsonObject = new JSONObject();
        for(JPVolume volume: this.volumes){
            System.out.println(mapper.writeValueAsString(volume));
        }
        return mapper.writeValueAsString(this);
    }

    public void writeBook() throws IOException {
        writer.write(this.book, outputStream);
    }

    public ByteArrayOutputStream getOutputStream(){
        return this.outputStream;
    }

}
