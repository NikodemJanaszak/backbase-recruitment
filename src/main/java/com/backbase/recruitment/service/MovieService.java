package com.backbase.recruitment.service;

import com.backbase.recruitment.model.Movie;
import com.backbase.recruitment.model.MovieDto;
import com.backbase.recruitment.model.MovieEntityMapper;
import com.backbase.recruitment.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public Movie getById(Long id) {
        return movieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<MovieDto> getAll() {
        return movieRepository.findAll().stream()
                .map(MovieEntityMapper::movieToDto)
                .collect(Collectors.toList());
    }

    public void saveAll(List<Movie> movies) {
        movieRepository.saveAll(movies);
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public MovieDto addRating(Long movieId, Long rating) throws IncorrectRatingException {
        if (rating > 10) {
            throw new IncorrectRatingException("Rating cannot be over 10");
        }
        Movie movie = getById(movieId);
        movie.setVotesNumber(movie.getVotesNumber() + 1);
        movie.setVotesSum(movie.getVotesSum() + rating);

        return MovieEntityMapper.movieToDto(save(movie));
    }

    public List<MovieDto> getTopTenByRatingOrderByBoxOffice() {
        List<Movie> movies = movieRepository.findTop10ByOrderByVotesSumDesc();
        return movies.stream()
                .sorted(Comparator.comparing(Movie::getBoxOffice).reversed())
                .map(MovieEntityMapper::movieToDto)
                .collect(Collectors.toList());
    }
}
