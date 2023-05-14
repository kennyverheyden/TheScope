package thescope.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import thescope.models.User;
import thescope.models.UserRole;
import thescope.processors.LoginProcessor;
import thescope.processors.UserDetailsImpl;
import thescope.services.BookingService;
import thescope.services.UserService;

@Controller
public class SignUpController {

	@Autowired
	private UserService userService;
	@Autowired
	private LoginProcessor loginProcessor;
	@Autowired
	private BookingService bookingService; // If customer made a booking, redirect auto to booking landing page
	@Autowired
	private UserDetailsImpl userDetails;

	public SignUpController() {}


	@GetMapping("/signup") // get request
	public String loginGet(Model model) {
		model.addAttribute("content", "signup");
		return "index";
	}

	@PostMapping("/signup") 
	public String signupUser(@RequestParam (required = false) String username, @RequestParam (required = false) String password, @RequestParam (required = false) String confirmpassword, @RequestParam (required = false) String name, @RequestParam (required = false) String firstname, @RequestParam (required = false) String address, @RequestParam (required = false) String postalcode, @RequestParam (required = false) String town, Model model, RedirectAttributes rm){
		//username = email
		Long role=5L; // 5 = customer
		if(!username.equals("") && !password.equals("") && !confirmpassword.equals("") && !name.equals("") && !firstname.equals("") && !address.equals("") && !town.equals(""))
		{
			if(!userService.userExist(username))
			{
				if(password.equals(confirmpassword))
				{
					// Register user
					User user=userService.createUser(username, password, name, firstname, address, postalcode, town, role);
					userDetails.setUser(user);

					if(bookingService.getBookedSchedule()!=null)// If customer made a booking, redirect auto to booking landing page
					{
						model.addAttribute("content", "booknow");
						return "redirect:/booknow";
					}
					else
					{
						return "redirect:/main";
					}
				}
				else
				{
					// Check if password boxes are the same
					model.addAttribute("content", "signup");
					rm.addFlashAttribute("message","Confirmation password not the same");
					return "redirect:/signup";
				}
			}
			else
			{
				// Check if all fields are filled in
				model.addAttribute("content", "signup");
				rm.addFlashAttribute("message","Username (email) already taken");
				return "redirect:/signup";
			}
		}
		else
		{
			// Check if all fields are filled in
			model.addAttribute("content", "signup");
			rm.addFlashAttribute("message","Fill in all fields");
			return "redirect:/signup";
		}
	}


}
