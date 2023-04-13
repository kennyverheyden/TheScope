package thescope.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import thescope.models.Movie;
import thescope.services.MovieService;

@Controller
public class HomeController {
	@Autowired
	private MovieService movieService;

	public HomeController() {

	}


	@GetMapping("/") // get request
	public String selectGet(Model model) {
		model.addAttribute("content", "home"); // redirect to movie view (home.html)
		return "index";
	}

	@PostMapping("/home/findMovie") 
	public String findMovie(@RequestParam (required = false) String searchString, Model model, RedirectAttributes rm){

		List<Movie> movies=movieService.findMoviesByGenre(searchString);
		if(movies.isEmpty()) // Search movie titles when the user did not search on genre
		{
			System.out.println(searchString);
			movies = movieService.findMoviesByTitle(searchString);
		}

		model.addAttribute("content", "home");
		if(!movies.isEmpty()) {
			model.addAttribute("title", "Your search result for: " +searchString);
		}
		model.addAttribute("movies",movies);  // map content to html elements
		return "index";
	}
}
