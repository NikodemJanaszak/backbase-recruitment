package com.backbase.recruitment.model.Movie;

public class MovieEntityMapper {

    public static MovieDTO MovieToDto(Movie movie) {
        double avgRating = roundDouble(movie.getSummaryVoting().doubleValue() / movie.getVotes().doubleValue());
        return new MovieDTO(movie.getId(), movie.getTitle(),
                movie.getDirector(), movie.isWonAward(), movie.getWonYear(),
                movie.getBoxOffice(), movie.getVotes(), avgRating);
    }

    private static Double roundDouble(Double avgRating) {
        double scale = Math.pow(10, 1);
        return Math.round(avgRating * scale) / scale;
    }
}