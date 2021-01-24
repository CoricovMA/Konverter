package org.konverter.JPMTL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Paragraph {

    @JsonProperty("type")
    private String type;

    @JsonProperty("content")
    private String content;

    public String getContent(){
        return String.format("%s ", this.content);
    }
}