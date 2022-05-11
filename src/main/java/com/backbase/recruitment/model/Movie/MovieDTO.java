package com.backbase.recruitment.model.Movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MovieDTO {
    private Long id;

    private String title;

    private String director;

    private boolean wonAward;

    private String wonYear;

    private String BoxOffice;

    private Long votes;

    private Double avgRating;
}
