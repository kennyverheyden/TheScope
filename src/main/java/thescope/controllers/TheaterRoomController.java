package thescope.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import thescope.models.TheaterRoom;
import thescope.repositories.TheaterRoomRepository;

@Controller
public class TheaterRoomController {

	private final TheaterRoomRepository theaterRoomRepository;

	@Autowired
	public TheaterRoomController(TheaterRoomRepository theaterRoomRepository) {
		this.theaterRoomRepository = theaterRoomRepository;
	}

	@GetMapping("/rooms") // get request
	public String selectGet(Model model) {

		//	List<TheaterRoom> rooms = theaterRoomRepository.findAll();
		model.addAttribute("content", "rooms"); // redirect to movie view (rooms.html)
		//model.addAttribute("rooms",rooms);  // map content to html elements
		return "index";
	}
}
