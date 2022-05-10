package com.backbase.recruitment.controller;

import com.backbase.recruitment.model.Movie;
import com.backbase.recruitment.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/movies")
@Controller
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<Movie> getAll() {
        return movieService.findAll();
    }
}
