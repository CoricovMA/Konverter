package org.epubmaker.Exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Error {

    private static final ObjectMapper mapper = new ObjectMapper();

    @JsonProperty("status")
    private String status;

    @JsonProperty("cause")
    private String cause;

    public Error(){
        this.status = "error";
        this.cause = "unknown";
    }

    public Error(String cause){
        this();
        this.cause = cause;
    }

    public String toString(){
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
