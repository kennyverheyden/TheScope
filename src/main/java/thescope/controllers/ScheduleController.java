package thescope.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleController {
	@GetMapping("/schedule") // get request
	public String schedule(Model model) {
		model.addAttribute("content","schedule"); // redirect to schedule.html
		return "index";
	}
	
}
