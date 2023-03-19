package thescope.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import thescope.services.UserService;

@Controller
public class MainController {

	private final UserService userService;
	MainController(UserService userService)
	{
		this.userService=userService;
	}

	@GetMapping("/main")
	public String home(@RequestParam(required = false)String logout, Model model)
	{
		if(logout != null) {
			userService.setUsername(null);
			userService.setSecret(null);
		}

		String username = userService.getUsername();
		// When user is not logged on, the String is null

		if(username==null)
		{
			model.addAttribute("content", "login");
			return "index";
		}

		// When user is logged in, the user will be directed to another page
		System.out.println(userService.getName());
		model.addAttribute("name",userService.getFirstname()+" "+userService.getName());
		model.addAttribute("content", "main");
		return "index";
	}

}
