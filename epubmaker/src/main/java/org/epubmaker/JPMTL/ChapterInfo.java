package org.epubmaker.JPMTL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.epubmaker.Request.RequestMaker;

import java.io.IOException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChapterInfo {

    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    @JsonProperty("title")
    private String title;

    @JsonProperty("index")
    private int index;

    @JsonProperty("id")
    private long chapterID;

    private Chapter chapter;

    public ChapterInfo(){
    };

    public long getChapterID(){
        return this.chapterID;
    }

    public Chapter getChapter(){
        return this.chapter;
    }

    public String string() throws JsonProcessingException {
        return mapper.writeValueAsString(this);
    }

    public void generateChapter(){
        try {
            this.chapter = RequestMaker.getChapterObject(this.chapterID);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
