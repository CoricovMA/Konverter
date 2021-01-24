package org.konverter.JPMTL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.service.MediatypeService;
import org.konverter.Util.EMObject;
import org.konverter.Util.HtmlMaker;

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
        sb.append(this.getTitleHtml());

        content.forEach(paragraph ->
                {
                    sb.append(HtmlMaker.getParagraph(paragraph.getContent().trim().strip()));
                }

        );

        return HtmlMaker.getDiv(sb.toString());
    }

    public Resource getAsResource() {
        return new Resource(this.getContentAsHtml().getBytes(), MediatypeService.XHTML);
    }

    public String getTitle() {
        return String.format("Chapter %s: %s", this.chapterIndex, this.title);
    }

    private String getTitleHtml() {
        return String.format("<h1>%s</h1>", this.getTitle());
    }

    public String prettyParagraphs() {

        String[] arr = this.getAllContent().split("\\.");

        StringBuilder sb = new StringBuilder();

        sb.append(HtmlMaker.getTitle(this.getTitle()));
        int count = 0;
        StringBuilder inner = new StringBuilder();
        for (String s : arr) {
            if (count == 5) {
                sb.append(HtmlMaker.getParagraph(inner.toString()));
                inner = new StringBuilder();
                inner.append(String.format("%s. ", s));
                count = 1;
            } else {
                count++;
                inner.append(String.format("%s. ", s));
            }
        }
        return HtmlMaker.getDiv(sb.toString());
    }
}