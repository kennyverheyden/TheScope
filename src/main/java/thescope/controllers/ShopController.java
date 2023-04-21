package thescope.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import thescope.models.Booking;
import thescope.models.ShopList;
import thescope.models.ShopListLine;
import thescope.models.ShopListOrder;
import thescope.services.BookingService;
import thescope.services.ShopListLineService;
import thescope.services.ShopListOrderService;
import thescope.services.ShopListService;
import thescope.services.UserService;


@Controller
public class ShopController {

	@Autowired
	private ShopListService shopListService;
	@Autowired
	private UserService userService; // If you want to print user name on shop page
	@Autowired
	ShopListLineService shopListLineService;
	@Autowired
	ShopListOrderService shopListOrderService;
	@Autowired
	BookingService bookingService;
	
	List<ShopList> filteredProducts = new ArrayList(); // Products FILTERED by category
	List<ShopList> selectedProducts= new ArrayList(); // Collect SELECTED product from user

	
	public ShopController() {}

	@GetMapping("/shop") // get request
	public String selectGet(Model model) {

		List<ShopList> products = shopListService.findAll(); 
		model.addAttribute("content", "shop"); // Map to shop page
		model.addAttribute("welcomeName",userService.getName()); // print user name on shop page
		model.addAttribute("role",userService.getUserRole()); // print user role name on shop page
		model.addAttribute("products", products); // Bind products array to html elements
		model.addAttribute("selectedProducts",selectedProducts); // Bind SELECTED products array to html elements
		return "index";
	}

	@PostMapping("/shop/filter") 
	public String filterProduct(@RequestParam (required = false) String cat, Model model){

		// Filter products from search on shop page
		List<ShopList> filteredProducts = shopListService.findByCatFilter(cat);

		model.addAttribute("content", "shop"); // Map to shop page
		model.addAttribute("welcomeName",userService.getName()); // Print user name on shop page
		model.addAttribute("role",userService.getUserRole()); // Print user role name on shop page
		model.addAttribute("products",filteredProducts); // Bind FILTERED products array to html elements
		model.addAttribute("selectedProducts",selectedProducts); // Bind SELECTED products array to html elements
		return "index";
	}
	
	@PostMapping("/shop/select") 
	public String selectProduct(@RequestParam (required = false) Long select, Model model){

		// Collect selected product from user
		if(select!=null)
		{
		selectedProducts.add(shopListService.findShopListById(select));
		}
		
		model.addAttribute("content", "shop"); // Map to shop page
		model.addAttribute("welcomeName",userService.getName()); // Print user name on shop page
		model.addAttribute("role",userService.getUserRole()); // Print user role name on shop page
		model.addAttribute("products",shopListService.findAll()); // Otherwise the choose products will be empty
		model.addAttribute("selectedProducts",selectedProducts); // Bind SELECTED products array to html elements
		return "index";
	}
	
	@PostMapping("/shop/clearSelectedItems") 
	public String selectProduct(Model model){

		// Collect selected product from user
		selectedProducts.clear();
		
		model.addAttribute("content", "shop"); // Map to shop page
		model.addAttribute("welcomeName",userService.getName()); // Print user name on shop page
		model.addAttribute("role",userService.getUserRole()); // Print user role name on shop page
		model.addAttribute("products",shopListService.findAll()); // Otherwise the choose products will be empty
		model.addAttribute("selectedProducts",selectedProducts); // Bind SELECTED products array to html elements
		return "index";
	}

	@PostMapping("/shop/buySelectedItems")
	public String buySelectedItems(Model model) {
		System.out.println("Hallokes " +userService.getName());
		
		
		Booking currentBooking= bookingService.findBookingByUser(userService.getName());
		System.out.println(currentBooking.getPKbooking()+" aaaaaaaa");
	
		for (ShopList shopList : selectedProducts) {
			ShopListLine s=new ShopListLine(shopList,1);
			shopListLineService.addShopListLine(s);
			shopListOrderService.addShopListOrder(new ShopListOrder(currentBooking,s ));
		}
		selectedProducts.clear();
		
		
		model.addAttribute("content", "shop"); // Map to shop page
		model.addAttribute("welcomeName",userService.getName()); // Print user name on shop page
		model.addAttribute("role",userService.getUserRole()); // Print user role name on shop page
		model.addAttribute("products",shopListService.findAll()); // Otherwise the choose products will be empty
		model.addAttribute("selectedProducts",selectedProducts); // Bind SELECTED products array to html elements
		return "index";
		
	}
}
