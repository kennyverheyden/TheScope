package thescope.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import thescope.services.CleaningScheduleService;

@Controller
public class CleaningScheduleController {

	@Autowired
	private CleaningScheduleService cleaningScheduleService;

	public CleaningScheduleController() {}
	

	@GetMapping("/cleanings") // get request
	public String selectGet(Model model) {

		model.addAttribute("content", "cleanings");
		//	model.addAttribute("cleanings",cleanings);  // map content to html elements
		return "index";
	}

}
