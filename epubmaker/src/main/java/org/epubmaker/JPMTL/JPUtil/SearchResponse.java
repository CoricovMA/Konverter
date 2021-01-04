package org.epubmaker.JPMTL.JPUtil;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.tomcat.util.threads.ResizableExecutor;
import org.epubmaker.Response.Response;
import org.epubmaker.Util.EMObject;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResponse implements EMObject, Response {

    @JsonProperty("novels")
    private List<NovelResponse> novels;

    public SearchResponse(){};

    public SearchResponse(List<NovelResponse> responses){
        this.novels = responses;
    }

    public String string() throws JsonProcessingException {

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }
}
