package com.backbase.recruitment.service;

import com.backbase.recruitment.constants.MovieConstants;
import com.backbase.recruitment.constants.MovieDtoConstants;
import com.backbase.recruitment.model.movie.Movie;
import com.backbase.recruitment.model.movie.MovieDto;
import com.backbase.recruitment.repository.MovieRepository;
import com.backbase.recruitment.service.exception.IncorrectRatingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieServiceTest {

    @MockBean
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    @Test
    public void shouldAddRatingToExistingMovie() throws IncorrectRatingException {
        MovieDto movieDto = MovieDtoConstants.toyStory;
        Movie movie = MovieConstants.toyStory;
        Mockito.when(movieRepository.findById(movieDto.getId())).thenReturn(Optional.of(MovieConstants.toyStory));

        MovieDto result = movieService.addRating(1L, 10L);

        movieDto.setAvgRating(5.9);
        movieDto.setVotes(11L);

        assertMovieDto(movieDto, result);
    }

    private void assertMovieDto(MovieDto expected, MovieDto result) {
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getBoxOffice(), result.getBoxOffice());
        assertEquals(expected.getDirector(), result.getDirector());
        assertEquals(expected.getTitle(), result.getTitle());
        assertEquals(expected.getWonYear(), result.getWonYear());
        assertEquals(expected.getVotes(), result.getVotes());
        assertEquals(expected.getAvgRating(), result.getAvgRating());
        assertEquals(expected.isWonAward(), result.isWonAward());

    }
}