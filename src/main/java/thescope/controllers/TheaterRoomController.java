package thescope.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import thescope.models.TheaterRoom;
import thescope.services.ScheduleShowService;
import thescope.services.TheaterRoomService;
import thescope.services.UserService;

@Controller
public class TheaterRoomController {

	@Autowired
	private TheaterRoomService theaterRoomService;
	@Autowired
	private UserService userService;
	@Autowired
	private ScheduleShowService scheduleShowService;

	public TheaterRoomController() {}


	@GetMapping("/rooms") // get request
	public String selectGet(Model model) {
		String username = userService.getUserName();
		// When user is not logged on, the String is null
		if(username==null)
		{
			model.addAttribute("content", "login");
			return "redirect:/";
		}

		List<TheaterRoom> rooms= theaterRoomService.findAllTheaterRooms();
		model.addAttribute("content", "rooms"); 
		model.addAttribute("rooms",rooms);  // map content to html elements
		return "index";
	}

	@PostMapping("/rooms/add") 
	public String addRoom(@RequestParam (required = false) String location, @RequestParam (required=false) int maxVipSeats, int maxNormalSeats, Model model, RedirectAttributes rm){
		if(!location.equals("") || maxVipSeats!=0 || maxNormalSeats!=0)
		{
			TheaterRoom room = new TheaterRoom();
			room.setLocation(location);
			room.setMaxNormalSeats(maxNormalSeats);
			room.setMaxVipSeats(maxVipSeats);
			theaterRoomService.addTheaterRoom(room);
			model.addAttribute("content", "rooms");
			// rm.addFlashAttribute("message","Fill in all fields");
			return "redirect:/rooms";
		}
		else
		{
			model.addAttribute("content", "rooms");
			rm.addFlashAttribute("message","Fill in all fields");
			return "redirect:/rooms";
		}
	}

	@PostMapping("/rooms/update") 
	public String updateRoom(@RequestParam (required=false) Long PKtheaterRoom,@RequestParam (required = false) String location, @RequestParam (required=false) int maxVipSeats, int maxNormalSeats, @RequestParam (required=false) boolean delete, Model model, RedirectAttributes rm){

		if(delete)
		{
			if(scheduleShowService.roomHasSchedule(theaterRoomService.findTheatherRoomById(PKtheaterRoom)))
			{
				model.addAttribute("content", "rooms");
				rm.addFlashAttribute("message","Cannot delete, this room has one or more schedules assigned");
				return "redirect:/rooms";
			}
			else
			{
				theaterRoomService.deleteTheaterRoomById(PKtheaterRoom);
				model.addAttribute("content", "rooms");
				rm.addFlashAttribute("message","Room deleted");
				return "redirect:/rooms";
			}
		}
		else
		{
			if(!location.equals("") || maxVipSeats!=0 || maxNormalSeats!=0 )
			{
				TheaterRoom room=theaterRoomService.findTheatherRoomById(PKtheaterRoom);
				room.setLocation(location);
				room.setMaxNormalSeats(maxNormalSeats);
				room.setMaxVipSeats(maxVipSeats);
				theaterRoomService.updateTheaterRoom(room);
				model.addAttribute("content", "rooms");
				rm.addFlashAttribute("message","Room updated");
				return "redirect:/rooms";
			}
			else
			{
				model.addAttribute("content", "rooms");
				rm.addFlashAttribute("message","Fill in all fields");
				return "redirect:/rooms";
			}
		}
	}

}
