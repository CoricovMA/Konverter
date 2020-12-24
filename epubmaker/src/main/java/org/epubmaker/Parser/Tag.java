package org.epubmaker.Parser;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.epubmaker.Request.BaseObject;

import java.io.IOException;

public class Tag implements BaseObject {

    @JsonProperty("findBy")
    public String findBy;

    @JsonProperty("value")
    public String value;

    public Tag(String findBy, String value){
        this.findBy = findBy;
        this.value = value;
    }

    @Override
    public BaseObject fromString(String json) {
        try {
            return objectMapper.readValue(json, this.getClass());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return null;
    }

    @Override
    public String toString(){
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
