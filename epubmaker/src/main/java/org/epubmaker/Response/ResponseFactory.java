package org.epubmaker.Response;

import org.epubmaker.Request.Request;

public class ResponseFactory {

    public static Response getResponse(Request request){
        switch (request.type){
            case JPM:
                return new Response() {
                    @Override
                    public int hashCode() {
                        return super.hashCode();
                    }
                };
            default:
                return null;
        }
    }

}
