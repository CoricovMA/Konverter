package org.epubmaker.Request;

import org.epubmaker.Response.Response;
import org.epubmaker.Response.ResponseFactory;

public class RequestHandler {

    public static Response doRequest(Request request){
        return ResponseFactory.getResponse(request);
    }

}
