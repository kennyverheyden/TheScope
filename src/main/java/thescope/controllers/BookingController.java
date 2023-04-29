package thescope.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import thescope.models.Booking;
import thescope.services.BookingService;
import thescope.services.UserService;

@Controller
public class BookingController {

	@Autowired
	private BookingService bookingService;
	@Autowired
	private UserService userService;

	public BookingController() {}


	@GetMapping("/bookingscustomer") // get request
	public String selectGet(Model model) {
		model.addAttribute("content", "bookingscustomer");
		//model.addAttribute("bookings",bookings);
		return "index";
	}

	@GetMapping("/bookingsstaff") // get request
	public String editBookingsGet(Model model) {
		String username = userService.getUserName();
		// When user is not logged on, the String is null
		if(username==null)
		{
			model.addAttribute("content", "login");
			return "redirect:/";
		}
		model.addAttribute("content", "bookingsstaff");
		//model.addAttribute("bookings",bookings);  // map content to html elements
		return "index";
	}
}
