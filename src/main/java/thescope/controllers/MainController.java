package thescope.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import thescope.processors.UserDetailsImpl;
import thescope.services.BookingService;

@Controller
public class MainController {

	@Autowired
	BookingService bookingService;
	@Autowired
	UserDetailsImpl userDetails;
	
	public MainController() {}

	@GetMapping("/main")
	public String home(@RequestParam(required = false)String logout, Model model)
	{
		model.addAttribute("content", "main");
		model.addAttribute("role",userDetails.getUser().getUserRole().getRoleName());
		model.addAttribute("welcomeName",userDetails.getUser().getFirstName()+" "+userDetails.getUser().getName());
		return "index";
	}

}
