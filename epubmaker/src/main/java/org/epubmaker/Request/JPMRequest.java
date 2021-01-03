package org.epubmaker.Request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;

public class JPMRequest extends Request implements BaseObject {

    /*
    list req example: https://jpmtl.com/v2/chapter/295/list?state=published&structured=true
    returns all chapter info for book 295

    chapter req example: https://jpmtl.com/v2/chapter/89796
    returns chapter information in structure:
        chapter: {
            content: [
                paragraphs
                ]
           }
     */

    @JsonProperty("url")
    private String url;

    @JsonProperty("type")
    private JPMTL_REQUEST_TYPE type;

    @JsonProperty("book_id")
    private long bookID;

    public enum JPMTL_REQUEST_TYPE{
        LIST,
        CHAPTER
    }

    public JPMRequest(String url, REQUEST_TYPE type) {
        super(url, type);
    }

    public void getAllChapters(){

    }

    @Override
    public BaseObject fromString(String json) throws IOException {
        return objectMapper.readValue(json, JPMRequest.class);
    }
}
