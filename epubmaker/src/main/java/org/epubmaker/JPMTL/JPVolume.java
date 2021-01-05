package org.epubmaker.JPMTL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.siegmann.epublib.domain.TOCReference;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JPVolume {

    @JsonProperty("id")
    private long id;

    @JsonProperty("volume")
    private long volume;

    @JsonProperty("volume_title")
    private String volumeTitle;

    @JsonProperty("chapters")
    private List<Chapter> chapters;

    public TOCReference getChaptersAsResource(){
        TOCReference volume = new TOCReference();

        chapters.forEach(chapter -> volume.addChildSection(chapter.getChapter()));

        return volume;
    }

    public long getVolumeIndex() {
        return this.volume;
    }
}
