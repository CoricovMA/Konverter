package org.konverter.JPMTL;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ChapterList {

    @JsonProperty("chapterList")
    private List<ChapterInfo> chapterInfoList;


    public List<ChapterInfo> getChapterInfoList(){
        return this.chapterInfoList;
    }
}
