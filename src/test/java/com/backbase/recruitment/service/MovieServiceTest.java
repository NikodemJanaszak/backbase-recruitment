package com.backbase.recruitment.service;

import com.backbase.recruitment.constants.MovieConstants;
import com.backbase.recruitment.constants.MovieDtoConstants;
import com.backbase.recruitment.model.movie.MovieDto;
import com.backbase.recruitment.repository.MovieRepository;
import com.backbase.recruitment.service.exception.IncorrectRatingException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class MovieServiceTest {

    private final MovieRepository movieRepository = Mockito.mock(MovieRepository.class);

    private final MovieService movieService = new MovieService(movieRepository);

    @Test
    public void shouldAddRatingToExistingMovie() throws IncorrectRatingException {
        MovieDto movieDto = MovieDtoConstants.toyStory;
        when(movieRepository.findById(movieDto.getId())).thenReturn(Optional.of(MovieConstants.toyStory));
        when(movieRepository.save(any())).thenReturn(MovieConstants.toyStory);

        MovieDto result = movieService.addRating(1L, 10L);

        assertEquals(11L, result.getVotes());
        assertEquals(5.9, result.getAvgRating());
    }

    @Test
    public void shouldThrowAnExceptionForBadRating() {
        assertThrows(IncorrectRatingException.class, () -> movieService.addRating(1L, 100L));
    }

    @Test
    public void shouldSortMoviesByBoxOfficeDescending() {
        String toyStory = "Toy Story";
        String sherlockHolmes = "Sherlock Holmes";
        String inception = "Inception";

        when(movieRepository.findTop10ByOrderByVotesSumDesc()).thenReturn(MovieConstants.getAll());

        List<MovieDto> topTenByBoxOffice = movieService.getTopTenByRatingOrderByBoxOffice();

        assertEquals(toyStory, topTenByBoxOffice.get(0).getTitle());
        assertEquals(sherlockHolmes, topTenByBoxOffice.get(1).getTitle());
        assertEquals(inception, topTenByBoxOffice.get(2).getTitle());
    }

}