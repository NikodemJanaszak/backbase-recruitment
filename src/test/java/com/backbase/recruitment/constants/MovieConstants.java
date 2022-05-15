package com.backbase.recruitment.constants;

import com.backbase.recruitment.model.movie.Movie;

public class MovieConstants {

    public static Movie toyStory = new Movie(
            1L,
            "Toy Story",
            "Toy Story Director",
            true,
            "2010",
            30000L,
            10L,
            55L
    );

    public static Movie inception = new Movie(
            2L,
            "Inception",
            "Inception Director",
            false,
            "2010",
            20000L,
            11L,
            44L
    );
}
