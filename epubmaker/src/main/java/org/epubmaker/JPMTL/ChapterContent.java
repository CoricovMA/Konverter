package org.epubmaker.JPMTL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.service.MediatypeService;
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

    public String getContentAsHtml(){
        StringBuilder sb = new StringBuilder();
        sb.append("<div>");

        content.forEach(paragraph -> sb.append(String.format("<p>%s</p>", paragraph.getContent().trim().strip())));

        sb.append("</div>");

        return sb.toString();
    }

    public Resource getAsResource(){
        return new Resource(this.getContentAsHtml().getBytes(), MediatypeService.XHTML);
    }

}
