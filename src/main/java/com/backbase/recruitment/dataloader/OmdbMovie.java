package com.backbase.recruitment.dataloader;

import com.backbase.recruitment.model.Movie;
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

    public Movie toMovie(com.backbase.recruitment.dataloader.AcademyAward award) {
        return new Movie(award.getTitle(), getDirector(), award.isWonAward(), award.getWonYear(),
                getValueFromBoxOffice(), 0L, 0L);
    }

    private Long getValueFromBoxOffice() {
        if (getBoxOffice() != null && !getBoxOffice().equals("N/A")) {
            return Long.valueOf(getBoxOffice().replaceAll("\\$", "").replaceAll(",", ""));
        }
        return 0L;
    }
}
