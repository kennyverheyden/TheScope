package thescope.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ShopController {

	
	public ShopController() {}
	
	@GetMapping("/shop") // get request
	public String selectGet(Model model) {

		model.addAttribute("content", "shop");
		return "index";

	}

}
