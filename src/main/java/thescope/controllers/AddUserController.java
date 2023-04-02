package thescope.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import thescope.services.UserService;

@Controller
public class AddUserController {

	// Injector login
	private final UserService userService;

	@Autowired
	public AddUserController(UserService userService)
	{
		this.userService=userService;
	}

	@GetMapping("/adduser") // get request
	public String loginGet(Model model) {
		String username = userService.getUserName();
		
		// When user is not logged on, the String is null
		if(username==null)
		{
			model.addAttribute("content", "login");
			return "redirect:/";
		}
		
		model.addAttribute("content", "adduser");
		return "index";
	}

	@PostMapping("/adduser") 
	public String commentPost(@RequestParam (required = false) String username, @RequestParam (required = false) String password, @RequestParam (required = false) String confirmpassword, @RequestParam (required = false) String name, @RequestParam (required = false) String firstname, @RequestParam (required = false) String address, @RequestParam (required = false) String postalcode, @RequestParam (required = false) String town, @RequestParam long role, Model model, RedirectAttributes rm){
		//username = email
		if(!username.equals("") && !password.equals("") && !confirmpassword.equals("") && !name.equals("") && !firstname.equals("") && !address.equals("") && !town.equals(""))
		{

			if(!userService.userExist(username))
			{


				if(password.equals(confirmpassword))
				{
					// Register user
					userService.createUser(username, password, name, firstname, address, postalcode, town, role);

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
				rm.addFlashAttribute("message","Username (email) already taken");
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
