package org.epubmaker.Request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Format {

    @JsonProperty("key")
    private String key;

    @JsonProperty("value")
    private String value;

    public Format(String findBy, String value){
        this.key = findBy;
        this.value = value;
    }



}
