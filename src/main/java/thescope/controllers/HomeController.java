package thescope.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import thescope.repositories.UserRepository;
import thescope.services.UserService;


@Controller
public class HomeController {

	private final UserService userService;
	private final UserRepository userRepository;
	boolean test=true;

	public HomeController(UserService userService, UserRepository userRepository)
	{
		this.userService=userService;
		this.userRepository=userRepository;
	}

	@GetMapping("/") // get request
	public String selectGet(Model model) {
		model.addAttribute("content", "home"); // redirect to movie view (home.html)
		return "index";
	}
}
