package thescope;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import thescope.models.Movie;
import thescope.services.MovieService;

@SpringBootTest
public class MovieTest {

	@Autowired
	MovieService ms;

	@Test
	public void addMovieTest() { // werkt nog niet, ik kijk er later op terug
		Movie testMovie = new Movie();
		testMovie.setTitle("Jumper");
		testMovie.setLength(88);
		testMovie.setGenre("Sci-Fi");
		testMovie.setThreeD(false);
		testMovie.setRating(6.1);
		ms.addMovie(testMovie);
		assertEquals(52, ms.findMoviesByTitle(testMovie.getTitle()));
		ms.deleteMovieById(52L);

	}

}
