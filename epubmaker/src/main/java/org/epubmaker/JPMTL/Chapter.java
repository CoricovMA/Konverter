package org.epubmaker.JPMTL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.siegmann.epublib.domain.*;
import nl.siegmann.epublib.service.MediatypeService;
import org.epubmaker.Request.BaseObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Chapter implements BaseObject {

    @JsonProperty("id")
    private long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("index")
    private int chapterIndex;

    @JsonProperty("content")
    private ChapterContent content;

    @Override
    public BaseObject fromString(String json) {
        return null;
    }

    public TOCReference getChapter(){
        TOCReference tocReference = new TOCReference();
        tocReference.setTitle(this.title);
        tocReference.setResource(content.getAsResource());
        return tocReference;
    }

    public String getTitle(){
        return this.title;
    }
}
