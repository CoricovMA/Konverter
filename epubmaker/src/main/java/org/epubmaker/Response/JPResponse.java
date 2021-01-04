package org.epubmaker.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JPResponse implements Response{

    public enum TYPE{
        CHAPTER,
        LIST,
        BOOK
    }


}
