package org.epubmaker.JPMTL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.domain.Resource;
import org.epubmaker.JPMTL.JPUtil.NovelResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        volumes.stream().forEach(
                volume -> {
                    volume.getChapters();
                }
        );
    }

    private List<Resource> getAllChapters(){
        List<Resource> allRes = new ArrayList<>();
        

    }

}
