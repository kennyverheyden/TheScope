package thescope.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import thescope.models.User;
import thescope.services.UserService;

@Controller
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService=userService;
	}

	@GetMapping("/users") // get request
	public String selectGet(Model model) {
		
		String username = userService.getUserName();
		// When user is not logged on, the String is null

		if(username==null)
		{
			model.addAttribute("content", "login");
			return "redirect:/";
		}

		List<User> users = userService.list();
		model.addAttribute("content", "users"); 
		model.addAttribute("users",users);  // map content to html elements
		return "index";
	}

	@PostMapping("/users") 
	public String commentPost(@RequestParam (required = false) String userName, @RequestParam (required = false) String name, @RequestParam (required = false) String firstName, @RequestParam (required = false) String address, @RequestParam (required = false) String postalCode, @RequestParam (required = false) String town, String secret, String userRole, Model model, RedirectAttributes rm){

		if(!name.equals("") && !firstName.equals("") && !address.equals("") && !postalCode.equals("") && !town.equals(""))
		{
			// Update user
			userService.updateUser(userName, name, firstName, address, postalCode, town, secret, userRole);
			model.addAttribute("content", "users");
			rm.addFlashAttribute("message","Information succesfully updated");
			return "redirect:users";
		}
		else
		{
			// Check if all fields are filled in
			model.addAttribute("content", "users");
			rm.addFlashAttribute("message","Fill in all fields");
			return "redirect:users";
		}
	}

}
