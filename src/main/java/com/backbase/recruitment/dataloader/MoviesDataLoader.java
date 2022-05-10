package com.backbase.recruitment.dataloader;

import com.backbase.recruitment.dataloader.util.AcademyAward;
import com.backbase.recruitment.dataloader.util.OmdbMovie;
import com.backbase.recruitment.model.Movie;
import com.backbase.recruitment.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
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
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MoviesDataLoader implements CommandLineRunner {

    private static final String BEST_PICTURE = "Best Picture";
    private static final String OMDB_URL = "http://www.omdbapi.com";
    private static final String API_KEY = "51fb52b";

    private final MovieService movieService;

    private List<AcademyAward> academyAwards = new ArrayList<>();

    private final List<Movie> movies = new ArrayList<>();

    @Override
    public void run(String... args) throws Exception {
        loadAwards();
        loadMovies();
        movieService.saveAll(movies);
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

        academyAwards = academyAwards.stream()
                .distinct()
                .collect(Collectors.toList());
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

            ResponseEntity<String> omdbMovieResponse = restTemplate.getForEntity("http://www.omdbapi.com/?apikey=51fb52b&t=Smilin'+Through", String.class);
            ObjectMapper mapper = new ObjectMapper();
            OmdbMovie omdbMovie = new OmdbMovie();
            try {
                JsonNode jsonNode = mapper.readTree(omdbMovieResponse.getBody());
                omdbMovie.setTitle(jsonNode.get("Title").toString().replaceAll("\"", ""));
                omdbMovie.setGenre(jsonNode.get("Genre").toString().replaceAll("\"", ""));
                omdbMovie.setBoxOffice(jsonNode.get("BoxOffice").toString().replaceAll("\"", ""));
                omdbMovie.setDirector(jsonNode.get("Director").toString().replaceAll("\"", ""));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            movies.add(new Movie(award.getTitle(), omdbMovie.getDirector(), award.isWonAward(), award.getWonYear(),
                    omdbMovie.getBoxOffice(), 0L, 0L));
        });

// TODO

//            OmdbMovie omdbMovieResponse = restTemplate.getForObject(urlBuilder.toString(), OmdbMovie.class);
//            System.out.println(urlBuilder);
//        OmdbMovie omdbMovieResponse = restTemplate.getForObject("http://www.omdbapi.com/?apikey=51fb52b&t=Smilin'+Through", OmdbMovie.class);
//        System.out.println(omdbMovieResponse);
    }
}
