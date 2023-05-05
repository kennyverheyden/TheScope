package thescope.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import thescope.models.Booking;
import thescope.services.BookingService;
import thescope.services.UserService;

@Controller
public class MainController {

	@Autowired
	private UserService userService;
	@Autowired
	BookingService bookingService;
	
	public MainController() {}

	
	@GetMapping("/main")
	public String home(@RequestParam(required = false)String logout, Model model)
	{
		if(logout != null) {
			userService.setUserName(null);
			userService.setSecret(null);
			userService.setFirstname(null);
			userService.setName(null);
			userService.setRoleID(0);
			userService.setUserRole(null);
		}

		String username = userService.getUserName();
		// When user is not logged on, the String is null

		if(username==null)
		{
			model.addAttribute("content", "login");
			return "redirect:/";
		}
		
		// When user is logged in, the user will be directed to another page

		model.addAttribute("role",userService.getUserRole());
		model.addAttribute("welcomeName",userService.getFirstname()+" "+userService.getName());
		model.addAttribute("content", "main");
		return "index";
	}

}
