package thescope.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import thescope.models.Movie;
import thescope.services.MovieService;

@Controller
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    
    @GetMapping("/movies") // get request
	public String selectGet(Model model) {
		List<Movie> movies = movieService.findAllMovies();
		model.addAttribute("content", "movies"); // redirect to movie view (movies.html)
		model.addAttribute("movies",movies);  // map content to html elements
		return "index";
	}
}
