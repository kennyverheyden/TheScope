package thescope.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import thescope.models.Movie;
import thescope.repositories.MovieRepository;

@Controller
public class MovieController {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    
    @GetMapping("/movies") // get request
	public String selectGet(Model model) {

		List<Movie> movies = movieRepository.findAll();
		model.addAttribute("content", "movies"); // redirect to movie view (movies.html)
		model.addAttribute("movies",movies);  // map content to html elements
		return "index";
	}
}
