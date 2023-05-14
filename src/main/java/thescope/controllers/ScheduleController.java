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
import thescope.models.Movie;
import thescope.models.ScheduleShow;
import thescope.models.TheaterRoom;
import thescope.services.BookingService;
import thescope.services.MovieService;
import thescope.services.ScheduleShowService;
import thescope.services.TheaterRoomService;

@Controller
public class ScheduleController {

	@Autowired
	private ScheduleShowService scheduleShowService;
	@Autowired
	private MovieService movieService; // Assign movie to Schedule
	@Autowired
	private TheaterRoomService theaterRoomService; // Show dropdown list in html
	@Autowired
	private BookingService bookingService; // User cannot delete schedule when booking is assigned

	public ScheduleController() {}

	@GetMapping("/schedule") // get request
	public String schedule(Model model) {

		List <ScheduleShow> schedules =scheduleShowService.findAll();
		List <Movie> movies = movieService.findAllMovies();
		List<TheaterRoom> rooms= theaterRoomService.findAllTheaterRooms();
		model.addAttribute("movies",movies); 
		model.addAttribute("schedules",schedules);
		model.addAttribute("rooms",rooms); 
		model.addAttribute("content","schedule"); // redirect to schedule.html
		return "index";
	}

	@PostMapping("/schedule/find") 
	public String findSchedule(@RequestParam (required = false) String room, Model model, RedirectAttributes rm){
		List <ScheduleShow> schedules =scheduleShowService.findByLocation(room);
		List <Movie> movies = movieService.findAllMovies();
		List<TheaterRoom> rooms= theaterRoomService.findAllTheaterRooms();
		model.addAttribute("movies",movies); 
		model.addAttribute("schedules",schedules);
		model.addAttribute("rooms",rooms); 
		model.addAttribute("content","schedule"); // redirect to schedule.html
		return "index";
	}

	@PostMapping("/schedule/add") 
	public String addSchedule(@RequestParam (required = false) Long movieID, @RequestParam (required = false) Long roomID,  @RequestParam (required = false) Date date,  @RequestParam (required = false) String time, Model model, RedirectAttributes rm)
	{
		if(movieID!=null || movieID!=0)
		{
			Time sqlTime=java.sql.Time.valueOf(time);
			if(!scheduleShowService.checkIfRoomIsOccupied(roomID, date, sqlTime))
			{
				ScheduleShow schedule = new ScheduleShow();
				schedule.setMovie(movieService.findMovieById(movieID));
				schedule.setTheaterRoom(theaterRoomService.findTheatherRoomById(roomID));
				schedule.setDate(date);
				schedule.setTime(sqlTime);
				scheduleShowService.addScheduleShow(schedule);
				model.addAttribute("content","schedule"); // redirect to schedule.html
				rm.addFlashAttribute("message","New schedule added");
				return "redirect:/schedule";
			}
			else
			{
				model.addAttribute("content","schedule"); // redirect to schedule.html
				rm.addFlashAttribute("message","Room is already planned");
				return "redirect:/schedule";
			}
		}
		else
		{
			model.addAttribute("content","schedule"); // redirect to schedule.html
			rm.addFlashAttribute("message","Select a movie from the dropdown list");
			return "redirect:/schedule";
		}
	}

	@PostMapping("/schedule/update") 
	public String updateSchedule(@RequestParam long schedulePK, @RequestParam (required = false) Long PKmovie, @RequestParam (required = false) Long PKtheaterRoom, @RequestParam (required = false) Date date, @RequestParam (required = false) Time time,  @RequestParam (required = false) Boolean delete, Model model, RedirectAttributes rm){
		ScheduleShow schedule =scheduleShowService.findScheduleShowById(schedulePK);
		if(delete==null) // avoid error Cannot invoke "java.lang.Boolean.booleanValue()" because "delete" is null
		{
			delete=false;
		}

		if(!delete)
		{
			if(!scheduleShowService.checkIfRoomIsOccupied(PKtheaterRoom, date, time))
			{
				schedule.setMovie(movieService.findMovieById(PKmovie));
				schedule.setTheaterRoom(theaterRoomService.findTheatherRoomById(PKtheaterRoom));
				schedule.setTime(time);
				schedule.setDate(date);
				scheduleShowService.addScheduleShow(schedule);
				model.addAttribute("content","schedule"); // redirect to schedule.html
				rm.addFlashAttribute("message","Schedule updated");
				return "redirect:/schedule";
			}
			else
			{
				model.addAttribute("content","schedule"); // redirect to schedule.html
				rm.addFlashAttribute("message","Room is already planned");
				return "redirect:/schedule";
			}
		}
		else
		{
			if(bookingService.bookingHasSchedule(schedule))
			{
				model.addAttribute("content","schedule"); // redirect to schedule.html
				rm.addFlashAttribute("message","Cannot delete, schedule has one or more bookings assigned");
				return "redirect:/schedule";
			}
			else
			{
				scheduleShowService.deleteScheduleShowById(schedulePK);
				model.addAttribute("content","schedule"); // redirect to schedule.html
				rm.addFlashAttribute("message","Schedule deleted");
				return "redirect:/schedule";
			}
		}
	}

	@GetMapping("/movieschedule") // get request
	public String movieSchedule(Model model) {
		 
		List <ScheduleShow> schedules =scheduleShowService.findAll();
		
		model.addAttribute("schedules",schedules);
		model.addAttribute("content","movieschedule"); // redirect to schedule.html
		return "index";
	}

}
