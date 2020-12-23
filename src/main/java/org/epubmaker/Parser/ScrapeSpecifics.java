package org.epubmaker.Parser;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.epubmaker.Request.BaseObject;

import java.io.IOException;
import java.util.List;

public class ScrapeSpecifics implements BaseObject {

    @JsonProperty("nextTag")
    private Tag nextTag = null;

    @JsonProperty("titleTag")
    private Tag titleTag = null;

    @JsonProperty("mainTextTag")
    private Tag mainTextTag = null;

    @JsonProperty("multipleTextTags")
    private boolean multipleTextTags = false;

    @JsonProperty("textTags")
    private List<String> textTags = null;

    public Tag getNextTag(){
        return this.nextTag;
    }

    public Tag getTitleTag() {
        return this.titleTag;
    }

    public Tag getMainTextTag() {
        return this.mainTextTag;
    }

    public boolean isMultipleTextTags() {
        return this.multipleTextTags;
    }

    public List<String> getTextTags() {
        return this.textTags;
    }

    @Override
    public BaseObject fromString(String json) {
        return null;
    }
}
