package thescope.services;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thescope.models.Movie;
import thescope.repositories.MovieRepository;
import java.util.List;

@Service
@Transactional
public class TheaterRoomService {

    private final EntityManager entityManager;
    private final MovieRepository movieRepository;
    @Autowired
    public TheaterRoomService(EntityManager entityManager, MovieRepository movieRepository) {
        this.entityManager = entityManager;
        this.movieRepository = movieRepository;
    }


    public Movie findMovieById(Long id) {
        return entityManager.find(Movie.class, id);
    }

    public Movie findMovieByGenre(String genre) {
        return entityManager.find(Movie.class, genre);
    }

    public List<Movie> findAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies;
    }

    public void addMovie(Movie movie) {
        entityManager.persist(movie);
    }

    public void deleteMovieById(Long id) {
        entityManager.remove(entityManager.find(Movie.class, id));
    }

}
