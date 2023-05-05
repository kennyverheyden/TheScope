package thescope.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import thescope.services.UserService;

@Controller
public class ShopstaffController {

	@Autowired
	private UserService userService;
	
	public ShopstaffController() {}
	
	@GetMapping("/shopstaff") // get request
	public String selectGet(Model model) {
		model.addAttribute("content", "shopstaff"); // Map to shop page
		model.addAttribute("welcomeName",userService.getName()); // print user name on shop page
		model.addAttribute("role",userService.getUserRole()); // print user role name on shop page
		return "index";
	}
	
	@GetMapping("/ordershoplist")
	public String ordershoplist(Model model) {
		model.addAttribute("content", "ordershoplist"); // Map to shop page
		model.addAttribute("welcomeName",userService.getName()); // print user name on shop page
		model.addAttribute("role",userService.getUserRole()); // print user role name on shop page
		return "index";
	}
	
	@GetMapping("/editshoplist")
	public String editshoplist(Model model) {
		model.addAttribute("content", "editshoplist"); // Map to shop page
		model.addAttribute("welcomeName",userService.getName()); // print user name on shop page
		model.addAttribute("role",userService.getUserRole()); // print user role name on shop page
		return "index";
	}
	
	@GetMapping("/newshoplist")
	public String newshoplist(Model model) {
		model.addAttribute("content", "newshoplist"); // Map to shop page
		model.addAttribute("welcomeName",userService.getName()); // print user name on shop page
		model.addAttribute("role",userService.getUserRole()); // print user role name on shop page
		return "index";
	}
}
