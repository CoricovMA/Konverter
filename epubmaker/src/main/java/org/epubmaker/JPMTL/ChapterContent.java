package org.epubmaker.JPMTL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.service.MediatypeService;
import org.epubmaker.Util.EMObject;

import java.nio.ByteBuffer;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChapterContent implements EMObject {

    @JsonProperty("id")
    private long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("index")
    private int chapterIndex;


    @JsonProperty("content")
    private List<Paragraph> content;

    public String getAllContent() {
        StringBuilder sb = new StringBuilder();

        content.forEach(paragraph ->
                {
                    sb.append(paragraph.getContent());
                }
        );

        return sb.toString();
    }

    public String getContentAsHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<div>");
        sb.append(this.getTitleHtml());

        content.forEach(paragraph ->
                {
                    sb.append(String.format("<p>%s</p>", paragraph.getContent().trim().strip()));
                }

        );

        sb.append("</div>");

        return sb.toString();
    }

    public Resource getAsResource() {
        return new Resource(this.getContentAsHtml().getBytes(), MediatypeService.XHTML);
    }

    public String getTitle() {
        return String.format("Chapter %s: %s", this.chapterIndex, this.title);
    }

    private String getTitleHtml(){
        return String.format("<h1>%s</h1>", this.getTitle());
    }
    
    private List<String> prettyParagraphs(){
        String fullChapter = this.getAllContent();
        byte [] bytes = fullChapter.getBytes();
        ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        return null;
    }
}
