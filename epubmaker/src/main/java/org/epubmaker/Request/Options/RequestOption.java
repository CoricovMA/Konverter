package org.epubmaker.Request.Options;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "parser")
@JsonSubTypes({
        @JsonSubTypes.Type(value = JPRequestOption.class, name = "jpmtl")})
public abstract class RequestOption {

    protected static final ObjectMapper mapper = new ObjectMapper();

    public String toString(){
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
