package org.epubmaker.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.epubmaker.Response.Response;

import java.io.IOException;

public class JPRequest extends Request implements BaseObject {

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
        list,
        chapter,
        book
    }

    public JPRequest(String url, REQUEST_TYPE type) {
        super(url, type);
    }

    @Override
    public Response execute() {
        return null;
    }

    @Override
    public BaseObject fromString(String json) throws IOException {
        return objectMapper.readValue(json, JPRequest.class);
    }

    private Response getList(){

    }

    private Response getChapter(){

    }

    private Response getBook(){

    }

}
