package com.backbase.recruitment.service;

import com.backbase.recruitment.model.Movie.Movie;
import com.backbase.recruitment.model.Movie.MovieDTO;
import com.backbase.recruitment.model.Movie.MovieEntityMapper;
import com.backbase.recruitment.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public Movie getById(Long id) {
        return movieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Movie findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public List<MovieDTO> getAll() {
        return movieRepository.findAll().stream()
                .map(MovieEntityMapper::MovieToDto)
                .collect(Collectors.toList());
    }

    public void saveAll(List<Movie> movies) {
        movieRepository.saveAll(movies);
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public MovieDTO addRating(Long movieId, Long rating) {
        Movie movie = getById(movieId);
        movie.setVotes(movie.getVotes() + 1);
        movie.setSummaryVoting(movie.getSummaryVoting() + rating);

        return MovieEntityMapper.MovieToDto(save(movie));
    }

    public List<Movie> getTop10ByRatingOrderByBoxOffice() {
        return movieRepository.findTop10ByOrderBySummaryVotingDesc();
    }
}
