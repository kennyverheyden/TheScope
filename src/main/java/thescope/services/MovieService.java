package thescope.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thescope.models.Movie;
import thescope.repositories.MovieRepository;

import java.util.List;

@Service
@Transactional
public class MovieService {

    /** enkel movierepo is geinjecteerd
     * methoden van JPArepo toegepast ipv entityManager
     * **/


    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie findMovieById(Long id) {
        return movieRepository.findById(id).get();
    }

    public List<Movie> findAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies;
    }

    public void addMovie(Movie movie) {
        movieRepository.save(movie);        //zelfde functie als persist normaal
    }

    public void deleteMovieById(Long id) {
        movieRepository.delete(movieRepository.findById(id).get());
    }

}
