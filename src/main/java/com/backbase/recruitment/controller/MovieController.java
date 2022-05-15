package com.backbase.recruitment.controller;

import com.backbase.recruitment.model.movie.MovieDto;
import com.backbase.recruitment.service.exception.IncorrectRatingException;
import com.backbase.recruitment.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/movies")
@RestController
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<MovieDto> getMovies() {
        return movieService.getAll();
    }

    @GetMapping("/top-ten")
    public List<MovieDto> getTop10ByRatingOrderByBoxOffice() {
        return movieService.getTopTenByRatingOrderByBoxOffice();
    }

    @PostMapping("/rate/{movieId}")
    public ResponseEntity<?> rateMovie(@PathVariable("movieId") Long id, @RequestParam Long rating) throws IncorrectRatingException {
        MovieDto movieDto = movieService.addRating(id, rating);
        return new ResponseEntity<>(movieDto, HttpStatus.OK);
    }
}