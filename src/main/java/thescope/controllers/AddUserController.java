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
import thescope.repositories.UserRepository;
import thescope.services.UserService;

@Controller
public class AddUserController {

	// Injector login
	private final UserRepository userRepository;
	private final UserService userService;
	@Autowired
	public AddUserController(UserRepository userRepository,UserService userService)
	{
		this.userRepository=userRepository;
		this.userService=userService;
	}

	@GetMapping("/adduser") // get request
	public String loginGet(Model model) {
		model.addAttribute("content", "adduser");
		return "index";
	}

	@PostMapping("/adduser") 
	public String commentPost(@RequestParam (required = false) String username, @RequestParam (required = false) String password, @RequestParam (required = false) String confirmpassword, @RequestParam (required = false) String name, @RequestParam (required = false) String firstname, @RequestParam (required = false) String address, @RequestParam (required = false) String postalcode, @RequestParam (required = false) String town, @RequestParam int role, Model model, RedirectAttributes rm){
		//username = email
		if(!username.equals("") && !password.equals("") && !confirmpassword.equals("") && !name.equals("") && !firstname.equals("") && !address.equals("") && !town.equals(""))
		{
			if(password.equals(confirmpassword))
			{
				// Register user
				List<User> users = userService.findAll();
				User user = new User(username, password, name, firstname, address, postalcode, town, role);
				userRepository.storeUsers(user);
				users.add(user);
				model.addAttribute("content", "adduser");
				rm.addFlashAttribute("message","User succesfully added");
				return "redirect:adduser";
			}
			else
			{
				// Check if password boxes are the same
				model.addAttribute("content", "adduser");
				rm.addFlashAttribute("message","Confirmation password not the same");
				return "redirect:adduser";
			}
		}
		else
		{
			// Check if all fields are filled in
			model.addAttribute("content", "adduser");
			rm.addFlashAttribute("message","Fill in all fields");
			return "redirect:adduser";
		}
	}


}
