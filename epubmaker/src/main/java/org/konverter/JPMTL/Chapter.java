package org.konverter.JPMTL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import nl.siegmann.epublib.domain.TOCReference;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Chapter implements BaseObject {

    @JsonProperty("chapter")
    private ChapterContent content;

    @Override
    public BaseObject fromString(String json) {
        return null;
    }

    public TOCReference getChapter(){
        TOCReference tocReference = new TOCReference();
        tocReference.setTitle(this.content.getTitle());
        tocReference.setResource(content.getAsResource());
        return tocReference;
    }

    public String string() throws JsonProcessingException {
        return objectMapper.writeValueAsString(this);
    }

    public String getFullChapter(){
        return content.getAllContent();
    }

    public String getHtml(){
        return content.prettyParagraphs();
    }

    public String getTitle(){
        return this.content.getTitle();
    }

}
