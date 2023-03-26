package thescope.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import thescope.models.User;
import thescope.repositories.UserRepository;
import thescope.services.UserService;

@Controller
public class UserController {

	private final UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/users") // get request
	public String selectGet(Model model) {

		List<User> users = userRepository.findAll();
		model.addAttribute("content", "users"); // redirect to movie view (users.html)
		model.addAttribute("users",users);  // map content to html elements
		return "index";
	}

}
