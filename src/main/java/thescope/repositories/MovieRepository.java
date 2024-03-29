package thescope.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import thescope.models.Genre;
import thescope.models.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findMoviesByGenre (Genre genre);
	List<Movie> findMoviesByTitleIgnoreCase(String title);
}
