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
public class AccountController {

	private final UserRepository userRepository;
	private final UserService userService;

	@Autowired
	public AccountController(UserRepository userRepository, UserService userService) {
		this.userRepository = userRepository;
		this.userService=userService;
	}

	@GetMapping("/account") // get request
	public String selectGet(Model model) {


		if(userService.getUserName()==null)
		{
			model.addAttribute("content", "login");
			return "index";
		}

		User user = userService.findUserByUsername(userService.getUserName());

		model.addAttribute("content", "account");
		model.addAttribute("userName",user.getUserName());  // map content to html elements
		model.addAttribute("firstName",user.getFirstName());  
		model.addAttribute("name",user.getName());
		model.addAttribute("address",user.getAddress());
		model.addAttribute("postalCode",user.getPostalCode());
		model.addAttribute("town",user.getTown());

		return "index";
	}

	@PostMapping("/account") 
	public String commentPost(@RequestParam (required = false) String userName, @RequestParam (required = false) String name, @RequestParam (required = false) String firstName, @RequestParam (required = false) String address, @RequestParam (required = false) String postalCode, @RequestParam (required = false) String town, Model model, RedirectAttributes rm){

		if(!name.equals("") && !firstName.equals("") && !address.equals("") && !postalCode.equals("") && !town.equals(""))
		{
			// Update user
			userService.updateUser(userName, name, firstName, address, postalCode, town);
			model.addAttribute("content", "account");
			rm.addFlashAttribute("message","Information succesfully updated");
			return "redirect:account";
		}
		else
		{
			// Check if all fields are filled in
			model.addAttribute("content", "account");
			rm.addFlashAttribute("message","Fill in all fields");
			return "redirect:account";
		}
	}

}