package com.backbase.recruitment.dataloader;

import com.backbase.recruitment.model.movie.Movie;
import com.backbase.recruitment.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MoviesDataLoader implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(MoviesDataLoader.class);

    private final MovieService movieService;
    private final AwardsLoader awardsLoader;
    private final MovieClient movieClient;

    @Override
    public void run(String... args) throws Exception {
        if (movieService.getAll().isEmpty()) {
            loadMovies();
        }
        logger.info("---Movies are loaded to database---");
    }

    private void loadMovies() {
        List<AcademyAward> academyAwards = awardsLoader.loadAwards();
        List<Movie> movies = movieClient.loadMovies(academyAwards);

        movieService.saveAll(movies);
    }
}
