package org.konverter.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.konverter.Exceptions.RequestNotImplementedException;
import org.konverter.Request.Options.RequestOption;
import org.konverter.Response.Response;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = JPRequest.class, name = "jp_req")})
public abstract class Request implements BaseObject{

    @JsonProperty("request_option")
    private RequestOption requestOption;

    @JsonProperty("url")
    private String url;

    public Request(){};

    public Request(String url){
        this.url = url;
    }

    public Response execute() throws RequestNotImplementedException {
        throw new RequestNotImplementedException("This request has not yet been implemented");
    };

    public String toString(){
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
