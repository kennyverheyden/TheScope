package thescope.controllers;

import java.util.List;
import java.util.Set;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thescope.models.Movie;
import thescope.services.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;


    public MovieController() {}
    
    @GetMapping("/all") // get request
	public String selectGet(Model model) {
		List<Movie> movies = movieService.findAllMovies();
		model.addAttribute("content", "movies"); // redirect to movie view (movies.html)
		model.addAttribute("movies",movies);  // map content to html elements
		return "index";
	}

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
        Movie movie = movieService.findMovieById(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Movie> updateMovie(@Valid @RequestBody Movie movie, @PathVariable Long id) {
        movieService.updateMovie(movie, id);
        return new ResponseEntity<>(movie, HttpStatus.ACCEPTED);
    }

    @PutMapping("/add")
    public ResponseEntity<HttpStatus> addMovie(@Valid @RequestBody Movie movie) {
        movieService.addMovie(movie);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
