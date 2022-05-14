package com.backbase.recruitment.model.movie;

public class MovieEntityMapper {

    public static MovieDto movieToDto(Movie movie) {
        double avgRating = calculateRating(movie);
        String boxOffice = boxOfficeToDollar(movie.getBoxOffice());

        return new MovieDto(movie.getId(), movie.getTitle(),
                movie.getDirector(), movie.isWonAward(), movie.getWonYear(),
                boxOffice, movie.getVotesNumber(), avgRating);
    }

    private static Double calculateRating(Movie movie) {
        return roundDouble(movie.getVotesSum().doubleValue() / movie.getVotesNumber().doubleValue());
    }

    private static Double roundDouble(Double avgRating) {
        double scale = Math.pow(10, 1);
        return Math.round(avgRating * scale) / scale;
    }

    private static String boxOfficeToDollar(Long boxOffice) {
        if (boxOffice == null || boxOffice == 0) {
            return "N/A";
        }
        return "$" + boxOffice;
    }
}