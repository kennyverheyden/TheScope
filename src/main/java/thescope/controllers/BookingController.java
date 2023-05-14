package thescope.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import thescope.models.Booking;
import thescope.models.ScheduleShow;
import thescope.processors.UserDetailsImpl;
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
	@Autowired
	private UserDetailsImpl userDetails;

	public BookingController() {}


	// Customer side (visible when customer is logged on)
	@GetMapping("/bookingscustomer") // get request
	public String selectGet(Model model) {

		// Avoid customer to come here when not logged on
		String username = userDetails.getUsername();
		if(username==null)
		{
			model.addAttribute("content", "booknownotloggedon");
			return "redirect:/booknownotloggedon";
		}

		List<Booking> customerBookings = bookingService.findByUser(userService.findUserByUsername(userDetails.getUsername()));
		model.addAttribute("bookings",customerBookings);
		model.addAttribute("content", "bookingscustomer");
		return "index";
	}

	// Staff side
	@GetMapping("/bookingsstaff") // get request
	public String editBookingsGet(Model model) {
	
		List<Booking> allBookings = bookingService.findAll();
		model.addAttribute("allBookings",allBookings);  // map content to html elements
		model.addAttribute("content", "bookingsstaff");
		return "index";
	}

	// 1 Receive and start processing booking
	@PostMapping("/booking/booknow") // get request
	public String bookNowPost(@RequestParam (required = false) Long PKschedule, Model model, RedirectAttributes rm) {

		// **************** //
		bookingService.setBookedSchedule(PKschedule); // Keep the scheduleID of the selected booking to book
		// **************** //

		// 2A When user is not logged on, the String is null
		if(userDetails.getUser()==null)
		{
			bookingService.setBookingStatus("To continue, please <strong><a href=\"/login\">login</a></strong> or <strong><a href=\"/signup\">create an account</a></strong>");
			model.addAttribute("content", "booknownotloggedon");
			return "redirect:/booknownotloggedon";
		}
		// 2B Customer is now logged on

		model.addAttribute("content", "booknow");
		return "redirect:/booknow";
	}

	// 3 Landing page when customer is logged on
	@GetMapping("/booknow") // get request
	public String bookNowtGet(Model model) {

		ScheduleShow schedule=scheduleService.findScheduleShowById(bookingService.getBookedSchedule());
		model.addAttribute("firstname", userService.findUserByUsername(userDetails.getUsername()).getFirstName());
		model.addAttribute("schedule", schedule);
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
	public String addBookingPost(@RequestParam (required = false) int seats, @RequestParam (required = false) int vipSeats, Model model, RedirectAttributes rm) {
		Long schedulePK = bookingService.getBookedSchedule(); // Get PK of selected schedule to book 
		if(bookingService.checkSeats(schedulePK, seats))
		{
			if(bookingService.checkVipSeats(schedulePK, vipSeats)) // If seats
			{
				return commitBooking(seats, vipSeats, model,rm); // Make the booking
			}
			else // No vip seats
			{
				model.addAttribute("content", "booknow");
				rm.addFlashAttribute("message","Sorry, no VIP seats available");
				return "redirect:/booknow";
			}
		}
		else // No normal seats
		{
			model.addAttribute("content", "booknow");
			rm.addFlashAttribute("message","Sorry, no seats available");
			return "redirect:/booknow";
		}
	}

	// Make the booking
	private String commitBooking(int seats, int vipSeats,Model model, RedirectAttributes rm)
	{
		// Add booking
		Booking booking = new Booking();
		booking.setUser(userService.findUserByUsername(userDetails.getUsername()));
		booking.setScheduleShow(scheduleService.findScheduleShowById(bookingService.getBookedSchedule()));
		booking.setSeats(seats);
		booking.setVipSeats(vipSeats);
		bookingService.addBooking(booking);

		// Update count seats for this schedule
		ScheduleShow show = scheduleService.findScheduleShowById(bookingService.getBookedSchedule());
		show.setCountSeats(show.getCountSeats()+seats);
		show.setCountVipSeats(show.getCountVipSeats()+vipSeats);
		scheduleService.addScheduleShow(show);

		model.addAttribute("message","Thank you, your booking has been succesfully added!");
		model.addAttribute("content", "bookingscustomer");
		return "redirect:/bookingscustomer";
	}

	@PostMapping("/bookingsstaff/deletebooking")
	public String deleteBookingPost(@RequestParam (required = false) Long pkBooking, Model model, RedirectAttributes rm) {
		System.out.println(pkBooking);		// Find the booking
		Booking booking = bookingService.findBookingById(pkBooking);

		// Update the seats
		ScheduleShow schedule = booking.getScheduleShow();
		schedule.setCountSeats(schedule.getCountSeats()-booking.getSeats());
		schedule.setCountVipSeats(schedule.getCountVipSeats()-booking.getVipSeats());
		scheduleService.addScheduleShow(schedule);

		// Delete the booking
		bookingService.deleteBooking(booking);

		model.addAttribute("content", "bookingsstaff");
		rm.addFlashAttribute("message","Booking deleted");
		return "redirect:/bookingsstaff";

	}
}