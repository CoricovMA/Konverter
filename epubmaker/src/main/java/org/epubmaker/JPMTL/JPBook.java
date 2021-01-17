package org.epubmaker.JPMTL;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.service.MediatypeService;
import org.epubmaker.Response.BookResponses.EMBook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class JPBook extends EMBook{

    private static final ObjectMapper mapper = new ObjectMapper();
    private ByteArrayOutputStream outputStream;

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
    public byte[] getBookAsBytes() {
        return new byte[0];
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void writeBook() throws IOException {
        writer.write(this.book, outputStream);
    }

    public ByteArrayOutputStream getOutputStream(){
        return this.outputStream;
    }

}
