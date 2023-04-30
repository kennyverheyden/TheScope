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
import thescope.models.ScheduleShow;
import thescope.services.BookingService;
import thescope.services.ScheduleShowService;
import thescope.services.UserService;

@Controller
public class BookingController {

	@Autowired
	private BookingService bookingService;
	@Autowired
	private UserService userService;
	@Autowired
	private ScheduleShowService scheduleService;

	public BookingController() {}


	// Customer side (visible when customer is logged on)
	@GetMapping("/bookingscustomer") // get request
	public String selectGet(Model model) {
		
		// Avoid customer to come here when not logged on
		String username = userService.getUserName();
		if(username==null)
		{
			model.addAttribute("content", "booknownotloggedon");
			return "redirect:/booknownotloggedon";
		}
		
		List<Booking> customerBookings = bookingService.findByUser(userService.findUserByUsername(userService.getUserName()));
		model.addAttribute("bookings",customerBookings);
		model.addAttribute("content", "bookingscustomer");
		return "index";
	}

	// Staff side
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

	// 1 Receive and start processing booking
	@PostMapping("/booking/booknow") // get request
	public String bookNowPost(@RequestParam (required = false) Long PKschedule, Model model, RedirectAttributes rm) {
		String username = userService.getUserName();
		bookingService.setBookedSchedule(PKschedule); // Keep the scheduleID
		// 2 When user is not logged on, the String is null
		if(username==null)
		{
			bookingService.setBookingStatus("To continue, please <strong><a href=\"/login\">login</a></strong> or <strong><a href=\"/signup\">create an account</a></strong>");
			model.addAttribute("content", "booknownotloggedon");
			return "redirect:/booknownotloggedon";
		}
		// 2 Customer is logged on
	//	bookingService.(PKschedule);
		model.addAttribute("content", "booknow");
		return "redirect:/booknow";
	}

	// 3 Landing page when customer is logged on
	@GetMapping("/booknow") // get request
	public String bookNowtGet(Model model) {
		
		// Avoid customer to come here when not logged on
		String username = userService.getUserName();
		if(username==null)
		{
			model.addAttribute("content", "booknownotloggedon");
			return "redirect:/booknownotloggedon";
		}
		
		ScheduleShow schedule=scheduleService.findScheduleShowById(bookingService.getBookedSchedule());
		model.addAttribute("firstname", userService.findUserByUsername(userService.getUserName()).getFirstName());
		model.addAttribute("movie", schedule.getMovie().getTitle());
		model.addAttribute("room", schedule.getTheaterRoom().getLocation());
		model.addAttribute("date", schedule.getDate());
		model.addAttribute("time", schedule.getTime());
		model.addAttribute("content", "booknow");
		return "index";
	}
	
	
	// 3 Landing page when the customer is not logged on
	@GetMapping("/booknownotloggedon") // get request
	public String bookNowNotLoggedInGet(Model model) {
		model.addAttribute("message",bookingService.getBookingStatus());
		model.addAttribute("content", "booknownotloggedon");
		return "index";
	}
	
	@PostMapping("/booking/addbooking") // get request
	public String addBookingPost(@RequestParam (required = false) int seats, @RequestParam (required = false) int VIPseats, Model model, RedirectAttributes rm) {
		
		Booking booking = new Booking();
		booking.setUser(userService.findUserByUsername(userService.getUserName()));
		booking.setScheduleShow(scheduleService.findScheduleShowById(bookingService.getBookedSchedule()));
		bookingService.addBooking(booking);
		
		model.addAttribute("message","Thank you, your booking has been succesfully added!");
		model.addAttribute("content", "bookingscustomer");
		return "redirect:/bookingscustomer";
	}
}
