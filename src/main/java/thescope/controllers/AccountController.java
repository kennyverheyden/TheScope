package thescope.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import thescope.models.User;
import thescope.processors.UserDetailsImpl;
import thescope.services.UserService;

@Controller
public class AccountController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserDetailsImpl userDetails;

	public AccountController() {}

	@GetMapping("/account") // get request
	public String selectGet(Model model) {

		User user = userService.findUserByUsername(userDetails.getUsername());

		model.addAttribute("content", "account");
		model.addAttribute("userName",user.getUserName());  // map content to html elements
		model.addAttribute("firstName",user.getFirstName());  
		model.addAttribute("name",user.getName());
		model.addAttribute("address",user.getAddress());
		model.addAttribute("postalCode",user.getPostalCode());
		model.addAttribute("town",user.getTown());

		return "index";
	}

	@PostMapping("/account") 
	public String updateAccount(@RequestParam (required = false) String userName, @RequestParam (required = false) String name, @RequestParam (required = false) String firstName, @RequestParam (required = false) String address, @RequestParam (required = false) String postalCode, @RequestParam (required = false) String town, Model model, RedirectAttributes rm){

		if(!name.equals("") && !firstName.equals("") && !address.equals("") && !postalCode.equals("") && !town.equals(""))
		{
			// Update user
			userService.updateAccount(userName, name, firstName, address, postalCode, town);
			model.addAttribute("content", "account");
			rm.addFlashAttribute("message","Information succesfully updated");
			return "redirect:account";
		}
		else
		{
			// Check if all fields are filled in
			model.addAttribute("content", "account");
			rm.addFlashAttribute("message","Fill in all fields");
			return "redirect:account";
		}
	}

}