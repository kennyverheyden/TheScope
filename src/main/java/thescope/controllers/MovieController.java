package thescope.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import thescope.configuration.FileUploadUtil;
import thescope.models.Movie;
import thescope.services.MovieService;

@Controller
public class MovieController {

	@Autowired
	private MovieService movieService;

	public MovieController() {}


	@GetMapping("/movies") // get request
	public String selectGet(Model model) {
		model.addAttribute("content", "movies"); // redirect to movie view (movies.html)
		model.addAttribute("movies",movieService.findAllMovies());  // map content to html elements
		return "index";
	}

	@GetMapping("/addmovies") // get request
	public String addMovie(Model model) {
		model.addAttribute("content", "addmovies"); // redirect to movie view (addmovies.html)
		model.addAttribute("genres", movieService.getGenres());  // map content to html elements
		model.addAttribute("movies",movieService.findAllMovies());  // map content to html elements
		return "index";
	}

	@PostMapping("/addmovies/add") 
	public String createMovie(@RequestParam (required = false) String title, @RequestParam (required = false) String genre, @RequestParam (required = false) double rating,@RequestParam (required = false)  boolean treeD, @RequestParam (required = false) int length, @RequestParam("image") MultipartFile multipartFile, Model model, RedirectAttributes rm){
		if(!title.equals("") && !genre.equals("") && rating!=0 && length!=0 && multipartFile!= null)
		{
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			Movie movie = new Movie();
			movie.setTitle(title);
			movie.setGenre(genre);
			movie.setRating(rating);
			movie.setLength(length);
			movie.setThreeD(treeD);
			movie.setPhoto(fileName);
			movieService.addMovie(movie);
			String uploadDir = "images/" + movie.getPKmovie();
			try {
				FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rm.addFlashAttribute("message","Movie added");
			model.addAttribute("content", "addmovies");
			return "redirect:/addmovies";
		}
		else
		{
			rm.addFlashAttribute("message","Fill in all fields");
			model.addAttribute("content", "addmovies");
			return "redirect:/addmovies";
		}
	}


}
