package org.epubmaker.JPMTL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.epubmaker.Util.EMObject;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChapterContent implements EMObject {

    @JsonProperty("content")
    private List<Paragraph> content;

    public String getContent(){
        StringBuilder sb = new StringBuilder();

        content.forEach(paragraph -> sb.append(paragraph.getContent().trim().strip()));

        return sb.toString();
    }

}
