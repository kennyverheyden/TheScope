package thescope.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import thescope.models.Booking;
import thescope.repositories.BookingRepository;

@Controller
public class BookingController {
	private final BookingRepository bookingRepository;

	@Autowired
	public BookingController(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}

	@GetMapping("/bookings") // get request
	public String selectGet(Model model) {

		//	List<Booking> bookings = bookingRepository.findAll();
		model.addAttribute("content", "bookings");
		//	model.addAttribute("bookings",bookings);  // map content to html elements
		return "index";
	}
}
