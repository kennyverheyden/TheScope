package thescope.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import thescope.models.CleaningSchedule;
import thescope.repositories.CleaningScheduleRepository;

@Controller
public class CleaningScheduleController {

	private final CleaningScheduleRepository cleaningScheduleRepository;

	@Autowired
	public CleaningScheduleController(CleaningScheduleRepository cleaningScheduleRepository) {
		this.cleaningScheduleRepository = cleaningScheduleRepository;
	}

	@GetMapping("/cleanings") // get request
	public String selectGet(Model model) {

		List<CleaningSchedule> cleanings = cleaningScheduleRepository.selectCleaningSchedule();
		model.addAttribute("content", "cleanings"); // redirect to movie view (cleanings.html)
		model.addAttribute("cleanings",cleanings);  // map content to html elements
		return "index";
	}

}
