package org.epubmaker.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

import java.io.IOException;

public class Format extends BaseObject {

    @JsonProperty("name")
    private String key;

    @JsonProperty("value")
    private ScrapeSpecifics value;

    public Format(String findBy, ScrapeSpecifics value) {
        this.key = findBy;
        this.value = value;
    }

    public Format fromJson(JSONObject object) throws IOException {
        return objectMapper.readValue(object.toString(), Format.class);
    }

    public Format setValue(ScrapeSpecifics value) {
        this.value = value;
        return this;
    }

    public Format setKey(String key) {
        this.key = key;
        return this;
    }


}
