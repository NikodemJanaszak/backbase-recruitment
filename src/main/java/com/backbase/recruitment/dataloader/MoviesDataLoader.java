package com.backbase.recruitment.dataloader;

import com.backbase.recruitment.model.Movie;
import com.backbase.recruitment.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MoviesDataLoader implements CommandLineRunner {

    private static final String BEST_PICTURE = "Best Picture";

    private static final String OMDB_URL = "http://www.omdbapi.com";

    private static final String API_KEY = "51fb52b";

    private final MovieService movieService;

    @Override
    public void run(String... args) throws Exception {
        if (movieService.getAll().isEmpty()) {
            loadMovies();
        }
    }

    private void loadMovies() {
        List<AcademyAward> academyAwards = loadAwards();
        List<Movie> movies = loadMovies(academyAwards);

        movieService.saveAll(movies);
    }

    private List<com.backbase.recruitment.dataloader.AcademyAward> loadAwards() {
        List<AcademyAward> academyAwards = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/dataloader/academy_awards.csv"))) {
            String line;

            int firstLine = 0;
            while ((line = reader.readLine()) != null) {
                if (firstLine == 0) {
                    firstLine++;
                    continue;
                }
                String[] fields = line.split(",");

                if (fields[1].equals(BEST_PICTURE)) {
                    academyAwards.add(createAcademyAward(fields));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return academyAwards.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    private AcademyAward createAcademyAward(String[] fields) {
        String wonYear = getWonYearFromCSV(fields[0]);
        String title = fields[2];
        Boolean wonAward = getWonAwardFromCSV(fields[fields.length - 1]);
        return new AcademyAward(wonYear, title, wonAward);
    }

    private String getWonYearFromCSV(String field) {
        String[] splitResult = field.split(" ");
        return splitResult[0];
    }

    private Boolean getWonAwardFromCSV(String field) {
        return field.equals("YES");
    }

    private List<Movie> loadMovies(List<AcademyAward> academyAwards) {
        final List<Movie> movies = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        academyAwards.forEach(award -> {
            OmdbMovie omdbMovie = restTemplate.getForObject(createUri(award), com.backbase.recruitment.dataloader.OmdbMovie.class);

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
