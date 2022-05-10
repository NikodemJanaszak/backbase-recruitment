package com.backbase.recruitment.service;

import com.backbase.recruitment.model.Movie;
import com.backbase.recruitment.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public Movie findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public void saveAll(List<Movie> movies) {
        movieRepository.saveAll(movies);
    }


}