package org.konverter.Request.Options;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JPRequestOption extends RequestOption{

    @JsonProperty("requested")
    private String requested;

    @JsonProperty("id")
    private String id;

    public String getRequested(){
        return requested;
    }

}
