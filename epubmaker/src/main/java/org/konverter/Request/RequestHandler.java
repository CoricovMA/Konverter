package org.konverter.Request;

import org.konverter.Response.Response;
import org.konverter.Response.ResponseFactory;

public class RequestHandler {


    public static Response doRequest(Request request){
        return ResponseFactory.getResponse(request);
    }

}
