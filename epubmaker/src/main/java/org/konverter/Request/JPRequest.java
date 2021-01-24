package org.konverter.Request;

import org.konverter.Response.Response;

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

    public JPRequest(){

    };

    public JPRequest(String url) {
        super(url);
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
        return null;
    }

    private Response getChapter(){
        return null;

    }

    private Response getBook(){
        return null;

    }

}
