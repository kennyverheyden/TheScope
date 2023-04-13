package thescope.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import thescope.processors.LoginProcessor;
import thescope.services.UserService;

@Controller
public class SignUpController {

	// Injector login
	private UserService userService;
	private LoginProcessor loginProcessor;

	
	public SignUpController() {}
	
	
	public SignUpController(UserService userService, LoginProcessor loginProcessor)
	{
		this.userService=userService;
		this.loginProcessor=loginProcessor;
	}

	@GetMapping("/signup") // get request
	public String loginGet(Model model) {
		model.addAttribute("content", "signup");
		return "index";
	}

	@PostMapping("/signup") 
	public String signupUser(@RequestParam (required = false) String username, @RequestParam (required = false) String password, @RequestParam (required = false) String confirmpassword, @RequestParam (required = false) String name, @RequestParam (required = false) String firstname, @RequestParam (required = false) String address, @RequestParam (required = false) String postalcode, @RequestParam (required = false) String town, Model model, RedirectAttributes rm){
		//username = email
		Long role=5L; // 5 = customer
		if(!username.equals("") && !password.equals("") && !confirmpassword.equals("") && !name.equals("") && !firstname.equals("") && !address.equals("") && !town.equals(""))
		{

			if(!userService.userExist(username))
			{


				if(password.equals(confirmpassword))
				{
					boolean loggedIn = false;
					
					// Register user
					userService.createUser(username, password, name, firstname, address, postalcode, town, role);
					
					loginProcessor.setUserName(username);
					loginProcessor.setSecret(password);
					loggedIn = loginProcessor.login();

				//	model.addAttribute("content", "signup");
				//	rm.addFlashAttribute("message","Welcome, your account has been successfully created!");
					return "redirect:main";
				}
				else
				{
					// Check if password boxes are the same
					model.addAttribute("content", "adduser");
					rm.addFlashAttribute("message","Confirmation password not the same");
					return "redirect:signup";
				}
			}
			else
			{
				// Check if all fields are filled in
				model.addAttribute("content", "signup");
				rm.addFlashAttribute("message","Username (email) already taken");
				return "redirect:signup";
			}
		}
		else
		{
			// Check if all fields are filled in
			model.addAttribute("content", "signup");
			rm.addFlashAttribute("message","Fill in all fields");
			return "redirect:signup";
		}
	}


}
