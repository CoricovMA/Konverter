package org.epubmaker.JPMTL;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class JPVolume {

    @JsonProperty("id")
    private long id;

    @JsonProperty("volume")
    private long volume;

    @JsonProperty("volume_title")
    private String volumeTitle;

    @JsonProperty("chapters")
    private List<Chapter> chapters;
}