package org.epubmaker.Request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.util.List;

public class ScrapeSpecifics extends BaseObject {

    @JsonProperty("nextTag")
    private String nextTag = "";

    @JsonProperty("titleTag")
    private String titleTag = "";

    @JsonProperty("mainTextTag")
    private String mainTextTag = "";

    @JsonProperty("multipleTextTags")
    private boolean multipleTextTags = false;

    @JsonProperty("textTags")
    private List<String> textTags = null;

    public ScrapeSpecifics fromJson(String json) throws IOException {
        return objectMapper.readValue(json, ScrapeSpecifics.class);
    }

    public String getNextTag(){
        return this.nextTag;
    }

    public String getTitleTag() {
        return titleTag;
    }

    public String getMainTextTag() {
        return mainTextTag;
    }

    public boolean isMultipleTextTags() {
        return multipleTextTags;
    }

    public List<String> getTextTags() {
        return textTags;
    }
}
