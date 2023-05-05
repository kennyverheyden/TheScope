package thescope.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import thescope.models.ShopList;
import thescope.services.UserService;

@Controller
public class OrderShoplistController {
	
	@Autowired
	private UserService userService;
	
	public OrderShoplistController() {}

	@GetMapping("/ordershoplist") // get request
	public String selectGet(Model model) {

		
		model.addAttribute("content", "ordershoplist"); // Map to ordershop page
		model.addAttribute("welcomeName",userService.getName()); // print user name on shop page
		model.addAttribute("role",userService.getUserRole()); // print user role name on shop page
		
		return "index";
	}
}
