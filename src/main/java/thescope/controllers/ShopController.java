package thescope.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import thescope.models.ShopList;
import thescope.services.ShopListService;
import thescope.services.UserService;


@Controller
public class ShopController {

	@Autowired
	private ShopListService shopListService;
	@Autowired
	private UserService userService; // If your want to print user name on shop page

	public ShopController() {}

	@GetMapping("/shop") // get request
	public String selectGet(Model model) {

		List<ShopList> products = shopListService.findAll(); 
		model.addAttribute("content", "shop");
		model.addAttribute("welcomeName",userService.getName()); // print user name on shop page
		model.addAttribute("role",userService.getUserRole()); // print user role name on shop page
		model.addAttribute("products", products); // Bind products array to html elements
		return "index";

	}

	@PostMapping("/shop/filter") 
	public String findUser(@RequestParam (required = false) String cat, Model model){

		// Filter products from search on shop page
		List<ShopList> filteredProducts = shopListService.findByCatFilter(cat);

		model.addAttribute("content", "shop");
		model.addAttribute("welcomeName",userService.getName()); // print user name on shop page
		model.addAttribute("role",userService.getUserRole()); // print user role name on shop page
		model.addAttribute("products", filteredProducts); // Bind products array to html elements
		return "index";
	}

}
