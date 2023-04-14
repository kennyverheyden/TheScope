package thescope.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
