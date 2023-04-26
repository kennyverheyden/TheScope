package thescope.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thescope.exceptions.EntityNotFoundException;
import thescope.models.Movie;
import thescope.repositories.MovieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovieService {

	/** enkel movierepo is geinjecteerd
	 * methoden van JPArepo toegepast ipv entityManager
	 * **/

	@Autowired
	private MovieRepository movieRepository;

	public MovieService() {}


	public Movie findMovieById(Long id) {
		Optional<Movie> entity = movieRepository.findById(id);      //exceptions worden opgevangen zonder overal throws exception te moeten bijvoegen
		Movie unwrappedMovie = unwrapMovie(entity, id);
		return unwrappedMovie;
	}

	public List<Movie> findMoviesByGenre (String genre) {
		return movieRepository.findMoviesByGenre(genre);
	}

	public List<Movie> findMoviesByTitle (String title) {
		return movieRepository.findMoviesByTitle(title);
	}

	public List<Movie> findAllMovies() {
		List<Movie> movies = movieRepository.findAll();
		return movies;
	}

	// Make list of genres without duplicates
	// Used for html frontend, select a genre
	public List<String> getGenres() {
		List<String> genres = new ArrayList();
		List<Movie> movies = movieRepository.findAll();
		for(int i=0;i<movies.size();i++){
			boolean duplicate = false;
			int j=0;
			while(j < genres.size()){
				if(movies.get(i).getGenre().equals(genres.get(j))) {
					duplicate = true;
					break;}
				j++;}
			if(!duplicate){
				genres.add(movies.get(i).getGenre());}
		}return genres;
	}

	//functies enkel toegankelijk na authenticatie als manager, etc ..
	public Movie updateMovie(Movie movie, Long id) {
		Optional<Movie> entity = movieRepository.findById(id);
		Movie unwrappedMovie = unwrapMovie(entity, id);
		unwrappedMovie.setTitle(movie.getTitle());
		unwrappedMovie.setGenre(movie.getGenre());
		unwrappedMovie.setLength(movie.getLength());
		unwrappedMovie.setRating(movie.getRating());
		unwrappedMovie.setThreeD(movie.isThreeD());
		unwrappedMovie.setPhoto(movie.getPhoto());
		movieRepository.save(unwrappedMovie);
		return unwrappedMovie;
	}

	public void addMovie(Movie movie) {
		movieRepository.save(movie);        //zelfde functie als persist normaal
	}

	public void deleteMovieById(Long id) {
		Optional<Movie> entity = movieRepository.findById(id);
		Movie unwrappedMovie = unwrapMovie(entity, id);
		movieRepository.delete(unwrappedMovie);
	}

	public static Movie unwrapMovie(Optional<Movie> movie, Long id) {   //optional<T> is een helper klasse om exceptions op te vangen
		if (movie.isPresent()) {
			return movie.get();
		} else {
			throw new EntityNotFoundException(id, Movie.class);   //entityNotFoundException(T.class, id)
		}
	}

}
