package com.backbase.recruitment.dataloader.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OmdbMovie implements Serializable {

    @JsonProperty
    private String Title;

    @JsonProperty
    private String Genre;

    @JsonProperty
    private String BoxOffice;

    @JsonProperty
    private String Director;

    @Override
    public String toString() {
        return "OmdbMovie{" +
                "Title='" + Title + '\'' +
                ", Genre='" + Genre + '\'' +
                ", BoxOffice='" + BoxOffice + '\'' +
                ", Director='" + Director + '\'' +
                '}';
    }
}
