package com.backbase.recruitment.dataloader;

import com.backbase.recruitment.dataloader.util.AcademyAward;
import com.backbase.recruitment.dataloader.util.OmdbMovie;
import com.backbase.recruitment.model.Movie;
import com.backbase.recruitment.repository.MovieRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class MoviesDataLoader implements CommandLineRunner {

    private static final String BEST_PICTURE = "Best Picture";
    private static final String OMDB_URL = "http://www.omdbapi.com";
    private static final String API_KEY = "51fb52b";

    public MovieRepository movieRepository;

    private final List<AcademyAward> academyAwards = new ArrayList<>();

    private final List<Movie> movies = new ArrayList<>();

    @Override
    public void run(String... args) throws Exception {
        loadAwards();
        loadMovies();
    }

    private void loadAwards() {
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
                    academyAwards.add(createAcademyAwards(fields));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        do testów
        AtomicInteger i = new AtomicInteger(1);
        academyAwards.forEach(a -> {
            System.out.print(i + ": ");
            System.out.println(a);
            i.incrementAndGet();
        });
    }

    private AcademyAward createAcademyAwards(String[] fields) {
        String wonYear = getWonYearFromCSV(fields[0]);
        String title = fields[2];
        Boolean wonAward = getWonAwardFromCSV(fields[4]);
        return new AcademyAward(wonYear, title, wonAward);
    }

    private String getWonYearFromCSV(String field) {
        String[] splitResult = field.split(" ");
        return splitResult[0];
    }

    private Boolean getWonAwardFromCSV(String field) {
        return field.equals("YES");
    }

    private void loadMovies() {
        RestTemplate restTemplate = new RestTemplate();
        academyAwards.forEach(award -> {
            StringBuilder urlBuilder = new StringBuilder(OMDB_URL + "/?apikey=" + API_KEY + "&t=");
            String[] movieTitle = award.getTitle().split("[ \\-]");

            for (int i = 0; i < movieTitle.length; i++) {
                if (i == 0) {
                    urlBuilder.append(movieTitle[i]);
                } else {
                    urlBuilder.append("+").append(movieTitle[i]);
                }
            }
// TODO

//            OmdbMovie omdbMovieResponse = restTemplate.getForObject(urlBuilder.toString(), OmdbMovie.class);
            System.out.println(urlBuilder);
        });
//        OmdbMovie omdbMovieResponse = restTemplate.getForObject("http://www.omdbapi.com/?apikey=51fb52b&t=Smilin'+Through", OmdbMovie.class);
//        System.out.println(omdbMovieResponse);
        ResponseEntity<String> omdbMovieResponse = restTemplate.getForEntity("http://www.omdbapi.com/?apikey=51fb52b&t=Smilin'+Through", String.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        OmdbMovie omdbMovie = new OmdbMovie();
        try {
            omdbMovie = mapper.readValue(omdbMovieResponse.getBody(), OmdbMovie.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(omdbMovie);

    }
}
