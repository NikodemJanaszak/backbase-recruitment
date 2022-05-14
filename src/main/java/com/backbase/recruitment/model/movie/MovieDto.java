package com.backbase.recruitment.model.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MovieDto {
    private Long id;

    private String title;

    private String director;

    private boolean wonAward;

    private String wonYear;

    private String BoxOffice;

    private Long votes;

    private Double avgRating;
}
