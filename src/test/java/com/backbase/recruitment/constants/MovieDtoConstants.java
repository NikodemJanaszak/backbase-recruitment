package com.backbase.recruitment.constants;

import com.backbase.recruitment.model.movie.MovieDto;

public class MovieDtoConstants {

    public static MovieDto toyStory = new MovieDto(
            1L,
            "Toy Story",
            "Toy Story Director",
            true,
            "2010",
            "$30000",
            10L,
            5.5
    );

    public static MovieDto inception = new MovieDto(
            2L,
            "Inception",
            "Inception Director",
            false,
            "2010",
            "$20000",
            11L,
            4.0
    );
}
