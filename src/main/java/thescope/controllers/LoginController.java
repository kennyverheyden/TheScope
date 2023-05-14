package thescope.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import thescope.processors.LoginProcessor;
import thescope.services.BookingService;

@Controller
public class LoginController {

	@Autowired
	private LoginProcessor loginProcessor;
	@Autowired
	private BookingService bookingService; // If customer made a booking, redirect auto to booking landing page

	public LoginController() {}


	@GetMapping("/login") // get request
	public String loginGet(Model model) {
		model.addAttribute("content", "login");
		return "index";
	}

	@PostMapping("/login/submit") 
	public String loginPost(@RequestParam String username, @RequestParam String password, Model model, RedirectAttributes rm) throws Exception {
		boolean loggedIn = false;

		try {
			loggedIn = loginProcessor.login(username, password);
		} catch (Exception e) {
			model.addAttribute("content", "login");
			rm.addFlashAttribute("message","Your credentials are incorrect");
			return "redirect:/login";
		}

		if(loggedIn == true)
		{
			if(bookingService.getBookedSchedule()!=null)// If customer made a booking, redirect auto to booking landing page
			{
				model.addAttribute("content", "booknow");
				return "redirect:/booknow";
			}
			else
			{
				model.addAttribute("content", "main");
				return "redirect:/main";
			}
		}
		else
		{
			model.addAttribute("content", "login");
			rm.addFlashAttribute("message","Your credentials are incorrect");
			return "redirect:/login";
		}


	}

}
