package org.konverter.JPMTL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.siegmann.epublib.domain.TOCReference;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Chapter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @JsonProperty("chapter")
    private ChapterContent content;

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
