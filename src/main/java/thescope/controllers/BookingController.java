package thescope.controllers;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		List<Booking> customerBookings = bookingService.findByUser(userService.findUserByUsername(userService.getUserName()));
		model.addAttribute("bookings",customerBookings);
		model.addAttribute("content", "bookingscustomer");
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
		List<Booking> allBookings = bookingService.findAll();
		model.addAttribute("allBookings",allBookings);  // map content to html elements
		model.addAttribute("content", "bookingsstaff");
		return "index";
	}

	@PostMapping("/booking/booknow") // get request
	public String bookNowPost(@RequestParam (required = false) Long PKmovie, Model model, RedirectAttributes rm) {
		System.out.println(PKmovie);
		String username = userService.getUserName();
		// When user is not logged on, the String is null
		if(username==null)
		{
			bookingService.setBookingStatus("To continue, please <a href=\"/login\">login</a> or <a href=\"/signup\">create an account</a>");
			model.addAttribute("content", "booknow");
			return "redirect:/booknow";
		}

		//model.addAttribute("allBookings",allBookings);  // map content to html elements
		bookingService.setBookingStatus(""+PKmovie);
		model.addAttribute("content", "booknow");
		return "redirect:/booknow";
	}

	@GetMapping("/booknow") // get request
	public String bookNowtGet(Model model) {
		model.addAttribute("message",bookingService.getBookingStatus());
		model.addAttribute("content", "booknow");
		return "index";
	}
}
