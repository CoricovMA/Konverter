package org.epubmaker.JPMTL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
}
