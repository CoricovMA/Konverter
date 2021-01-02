package org.epubmaker.Request;

import org.epubmaker.Parser.Format;
import org.epubmaker.Response.Response;

public abstract class Request {

    public enum REQUEST_TYPE{
        JPM
    }

    private String url;
    public final REQUEST_TYPE type;

    public Request(String url, REQUEST_TYPE type){
        this.url = url;
        this.type = type;
    }


}
