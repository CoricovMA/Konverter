package org.epubmaker.JPMTL;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.epubmaker.Request.RequestMaker;

import java.io.IOException;

public class ChapterInfo {

    @JsonProperty("title")
    private String title;

    @JsonProperty("index")
    private int index;

    @JsonProperty("id")
    private long chapterID;

    private Chapter chapter;

    public ChapterInfo(){
        try {
            this.chapter = RequestMaker.getChapterObject(this.chapterID);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    };

    public long getChapterID(){
        return this.chapterID;
    }

    public Chapter getChapter(){
        return this.chapter;
    }

}
