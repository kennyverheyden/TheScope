package thescope;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.transaction.annotation.Transactional;
import thescope.models.Genre;
import thescope.models.Movie;
import thescope.services.MovieService;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MovieTest {

    @Autowired
    private MovieService movieService;
    @Test
    @Transactional
    public void findMovieByIdTest(){
        Long id = 15L;
        assertNotNull(movieService.findMovieById(id));
    }
    @Test
    @Transactional
    public void findMovieByGenreTest() {
        String genre = "1";
        Movie movie = movieService.findMovieByGenre(genre);
        assertNotNull(movie);
    }

    @Test
    @Transactional
    void addMovieTest() {
        Movie movie = new Movie("Tetris", "2", 7.8, 118, true);
        movieService.addMovie(movie);
        assertNotNull(movie);
    }

    @Test
    @Transactional
    void deleteMovieById(){
        Long id = 30L;
        movieService.deleteMovieById(id);
        assertNull(movieService.findMovieById(id));

    }


}
