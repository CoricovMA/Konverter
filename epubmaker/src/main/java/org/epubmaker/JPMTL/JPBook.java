package org.epubmaker.JPMTL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Metadata;
import org.epubmaker.JPMTL.JPUtil.NovelResponse;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JPBook {

    private final List<JPVolume> volumes;
    private final NovelResponse novelInfo;
    private Book book;

    public JPBook(List<JPVolume> volumes, NovelResponse novelInfo){
        this.volumes = volumes;
        this.novelInfo = novelInfo;
    }

    public void generateBook(){
        book = new Book();
        Metadata metadata = book.getMetadata();
        metadata.addTitle(novelInfo.getTitle());
    }
}
