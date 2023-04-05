package thescope.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ShopController {

	@GetMapping("/shop") // get request
	public String selectGet(Model model) {

		model.addAttribute("content", "shop");
		return "index";

	}

}
