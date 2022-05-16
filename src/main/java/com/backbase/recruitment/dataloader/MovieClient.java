package com.backbase.recruitment.dataloader;

import com.backbase.recruitment.model.movie.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieClient {
    @Value("${dataLoader.url.omdbUrl}")
    private String OMDB_URL;

    @Value("${dataLoader.url.apiKey}")
    private String API_KEY;

    public List<Movie> loadMovies(List<AcademyAward> academyAwards) {
        final List<Movie> movies = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        academyAwards.forEach(award -> {
            OmdbMovie omdbMovie = restTemplate.getForObject(createUri(award), OmdbMovie.class);

            if (omdbMovie != null) {
                movies.add(omdbMovie.toMovie(award));
            }
        });

        return movies;
    }

    private URI createUri(AcademyAward award) {
        String movieTitle = award.getTitle()
                .replaceAll("[ \\-]", "+")
                .replaceAll("[\"]", "");
        return URI.create(OMDB_URL + "/?apikey=" + API_KEY + "&t=" + movieTitle);
    }
}
