package thescope.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import thescope.services.BookingService;

@Controller
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	public BookingController() {}
	
	
	@GetMapping("/bookings") // get request
	public String selectGet(Model model) {

		model.addAttribute("content", "bookings");
		//	model.addAttribute("bookings",bookings);  // map content to html elements
		return "index";
	}
}
