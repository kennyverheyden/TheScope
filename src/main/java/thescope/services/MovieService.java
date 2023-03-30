package thescope.services;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thescope.models.Genre;
import thescope.models.Movie;
import thescope.repositories.MovieRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MovieService {

    private final EntityManager entityManager;
    @Autowired
    public MovieService(EntityManager entityManager) {
        this.entityManager = entityManager;

    }
    public Movie findMovieById(Long id) {
        return entityManager.find(Movie.class, id);
    }
    public Movie findMovieByGenre(String genre) {
        return entityManager.find(Movie.class, genre);
    }

    public List<Movie> findAllMovies() {
        return new ArrayList<>();           //dummy statement
    }
    public void addMovie(Movie movie) {
        entityManager.persist(movie);
    }
    public void deleteMovieById(Long id) {
        entityManager.remove(entityManager.find(Movie.class, id));
    }

}
