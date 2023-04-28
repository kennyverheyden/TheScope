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
import thescope.services.TheaterRoomService;

@Controller
public class TheaterRoomController {

	@Autowired
	private TheaterRoomService theaterRoomService;
	
	public TheaterRoomController() {}
	

	@GetMapping("/rooms") // get request
	public String selectGet(Model model) {
		List<TheaterRoom> rooms= theaterRoomService.findAllTheaterRooms();
			model.addAttribute("content", "rooms"); 
		model.addAttribute("rooms",rooms);  // map content to html elements
		return "index";
	}
	
	@PostMapping("/rooms/add") 
	public String addRoom(@RequestParam (required = false) String location, @RequestParam (required=false) int maxVipSeats, int maxNormalSeats, Model model, RedirectAttributes rm){
			TheaterRoom room = new TheaterRoom();
			room.setLocation(location);
			room.setMaxNormalSeats(maxNormalSeats);
			room.setMaxVipSeats(maxVipSeats);
			theaterRoomService.addTheaterRoom(room);
			model.addAttribute("content", "rooms");
			// rm.addFlashAttribute("message","Fill in all fields");
			return "redirect:/rooms";
		
	}
	
	@PostMapping("/rooms/update") 
	public String updateRoom(@RequestParam (required = false) String location, @RequestParam (required=false) int maxVipSeats, int maxNormalSeats, Model model, RedirectAttributes rm){
			
		
			// update room 
		
		
			model.addAttribute("content", "rooms");
			// rm.addFlashAttribute("message","Fill in all fields");
			return "redirect:/rooms";
		
	}

}
