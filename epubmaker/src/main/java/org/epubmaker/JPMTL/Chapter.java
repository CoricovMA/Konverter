package org.epubmaker.JPMTL;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Chapter {

    @JsonProperty("id")
    private long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("created_at")
    private String createdAd;

    @JsonProperty("volume_id")
    private long volumeID;

    @JsonProperty("volume_title")
    private String volumeTitle;

    @JsonProperty("volume_index")
    private int volumeIndex;

    @JsonProperty("index")
    private int chapterIndex;
}
