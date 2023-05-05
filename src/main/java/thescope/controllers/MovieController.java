package thescope.controllers;

import java.io.File;
import java.io.IOException;

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
import thescope.services.ScheduleShowService;
import thescope.services.UserService;

@Controller
public class MovieController {

	@Autowired
	private MovieService movieService;
	@Autowired
	private UserService userService; // Check if user is logged on in case if the user access the page directly
	@Autowired
	private ScheduleShowService scheduleShowService; // User cannot delete a movie when assigned to one or more schedules

	public MovieController() {}

	// Open movie page
	@GetMapping("/movies") // get request
	public String selectGet(Model model) {
		model.addAttribute("content", "movies"); // redirect to movie view (movies.html)
		model.addAttribute("movies",movieService.findAllMovies());  // map content to html elements
		return "index";
	}

	// Open edit movie page
	@GetMapping("/editmovies") // get request
	public String editMovie(Model model) {
		String username = userService.getUserName();
		// When user is not logged on, the String is null
		if(username==null)
		{
			model.addAttribute("content", "login");
			return "redirect:/";
		}

		model.addAttribute("content", "editmovies"); // redirect to movie view (moviesedit.html)
		model.addAttribute("genres", movieService.getGenres());  // map content to html elements
		model.addAttribute("movies",movieService.findAllMovies());  // map content to html elements
		return "index";
	}

	// Open add movie page
	@GetMapping("/addmovies") // get request
	public String addMovie(Model model) {
		String username = userService.getUserName();
		// When user is not logged on, the String is null
		if(username==null)
		{
			model.addAttribute("content", "login");
			return "redirect:/";
		}

		model.addAttribute("content", "addmovies"); // redirect to movie view (addmovies.html)
		model.addAttribute("genres", movieService.getGenres());  // map content to html elements
		model.addAttribute("movies",movieService.findAllMovies());  // map content to html elements
		return "index";
	}

	// Add movie
	@PostMapping("/addmovies/add") 
	public String createMovie(@RequestParam (required = false) String title, @RequestParam (required = false) String genre, @RequestParam (required = false) double rating,@RequestParam (required = false)  boolean threeD, @RequestParam (required = false) int length, @RequestParam("image") MultipartFile multipartFile, Model model, RedirectAttributes rm){
		if(!title.equals("") && !genre.equals("") && multipartFile.getOriginalFilename().toString()!="")
		{
			// Determine the file name
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			Movie movie = new Movie();
			movie.setTitle(title);
			movie.setGenre(genre);
			movie.setRating(rating);
			movie.setLength(length);
			movie.setThreeD(threeD);
			movie.setPhoto(fileName);
			movieService.addMovie(movie);
			// Write uploaded file to hdd
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

	// Edit movie
	@PostMapping("/editmovies/edit") 
	public String editMovie(@RequestParam (required = false) Long movieID, @RequestParam (required = false) String title, @RequestParam (required = false) String genre, @RequestParam (required = false) double rating,@RequestParam (required = false)  boolean threeD, @RequestParam (required = false) int length, @RequestParam("image") MultipartFile image, @RequestParam (required = false) Boolean delete,Model model, RedirectAttributes rm){

		if(delete==null) // avoid error Cannot invoke "java.lang.Boolean.booleanValue()" because "delete" is null
		{
			delete=false;
		}

		if(!delete)
		{
			// Edit the movie
			if(!title.equals("") && !genre.equals("") && rating!=0 && length!=0)
			{

				Movie movie = movieService.findMovieById(movieID); // Load movie
				// Determine the filename
				String fileName = StringUtils.cleanPath(image.getOriginalFilename()); // Define filename
				movie.setTitle(title);
				movie.setGenre(genre);
				movie.setRating(rating);
				movie.setLength(length);
				movie.setThreeD(threeD);
				if(image.getOriginalFilename().toString()!="") // Check if image is uploaded or not
				{
					movie.setPhoto(fileName);
				}
				movieService.updateMovie(movie, movieID);
				// Write uploaded file to hdd
				if(image.getOriginalFilename().toString()!="") // If file is not uploaded
				{
					String uploadDir = "images/" + movie.getPKmovie(); // Define location
					try {
						FileUploadUtil.saveFile(uploadDir, fileName, image);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				rm.addFlashAttribute("message","Movie updated");
				model.addAttribute("content", "addmovies");
				return "redirect:/editmovies";
			}
			else
			{
				rm.addFlashAttribute("message","Fill in all fields");
				model.addAttribute("content", "addmovies");
				return "redirect:/editmovies";
			}
		}
		else
		{
			Movie movie = movieService.findMovieById(movieID); // Find movie
			if(scheduleShowService.movieHasSchedule(movie))
			{
				rm.addFlashAttribute("message","Cannot delete movie, movie assigned to one or more schedules");
				model.addAttribute("content", "addmovies");
				return "redirect:/editmovies";
			}
			else
			{
				// Delete movie
				String photo = movie.getPhoto(); // Get the filename
				String path = "images/"+movieID+"/"; // Get the folder, folder can only be deleted when empty
				File file = new File(path+photo);  // Construct the path and filename
				if (file.delete()) { // Delete the file
					File dir = new File(path); //// Init dir path
					dir.delete(); // Delete the directory
					movieService.deleteMovieById(movieID); // Delete the movie from the database
					rm.addFlashAttribute("message","Movie deleted"); // Send output message
					model.addAttribute("content", "addmovies");
					return "redirect:/editmovies";

				} else {
					System.out.println("Failed to delete the file.");
					rm.addFlashAttribute("message","Failed to delete the movie");
					model.addAttribute("content", "addmovies");
					return "redirect:/editmovies";
				} 
			}
		}
	}

}
