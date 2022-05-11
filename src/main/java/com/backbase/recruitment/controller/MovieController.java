package com.backbase.recruitment.controller;

import com.backbase.recruitment.model.Movie.Movie;
import com.backbase.recruitment.model.Movie.MovieDTO;
import com.backbase.recruitment.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/movies")
@RestController
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<MovieDTO> getAll() {
        return movieService.getAll();
    }

    @GetMapping("/top10")
    public List<Movie> getTop10ByRatingOrderByBoxOffice() {
        return movieService.getTop10ByRatingOrderByBoxOffice();
    }

    @PostMapping("/rate/{movieId}")
    public ResponseEntity<?> rateMovie(@PathVariable("movieId") Long id, @RequestParam Long rating) {
        try {
            if (rating > 10) {
                throw new IllegalArgumentException("Rating cannot be over 10");
            }
            return new ResponseEntity<>(movieService.addRating(id, rating), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

