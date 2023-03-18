package thescope.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
	
    @GetMapping("/") // get request
	public String selectGet(Model model) {

		model.addAttribute("content", "home"); // redirect to movie view (home.html)
		return "index";
	}
}
