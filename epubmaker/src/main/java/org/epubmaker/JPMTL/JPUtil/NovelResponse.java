package org.epubmaker.JPMTL.JPUtil;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.epubmaker.Exceptions.ParsingError;
import org.json.JSONObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NovelResponse{

    private static final ObjectMapper mapper = new ObjectMapper();

    @JsonProperty("id")
    private long novelID;

    @JsonProperty("cover")
    private String coverUrl;

    @JsonProperty("chapter_count")
    private int chapterCount;

    @JsonProperty("title")
    private String title;

    public NovelResponse(){};

    public NovelResponse(long id, String coverUrl, String title, int chapterCount){
        this.chapterCount = chapterCount;
        this.coverUrl = coverUrl;
        this.novelID = id;
        this.title = title;
    }

    public String toString(){
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return new ParsingError().toString();
        }
    }

}
