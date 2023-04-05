package thescope.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import thescope.services.TheaterRoomService;

@Controller
public class TheaterRoomController {

	private final TheaterRoomService theaterRoomService;

	@Autowired
	public TheaterRoomController(TheaterRoomService theaterRoomService) {
		this.theaterRoomService= theaterRoomService;
	}

	@GetMapping("/rooms") // get request
	public String selectGet(Model model) {

		model.addAttribute("content", "rooms"); 
		//model.addAttribute("rooms",rooms);  // map content to html elements
		return "index";
	}
}
