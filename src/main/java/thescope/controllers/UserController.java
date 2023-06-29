package thescope.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import thescope.models.User;
import thescope.models.UserRole;
import thescope.processors.UserDetailsImpl;
import thescope.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserDetailsImpl userDetails;
	
	// Store search parameter to keep the info on screen while editing
	private String findUserName;
	private String findName;
	private String findFirstName;
	private String roleName;

	public UserController() {}


	@GetMapping("/users") // get request
	public String selectGet(Model model) {

		List<User> foundUsers = userService.findUsers(findUserName, findName, findFirstName, roleName);
		List<UserRole> roles = userService.userRoles();

		model.addAttribute("users",foundUsers); 
		model.addAttribute("roles",roles);  // map content to html elements
		model.addAttribute("content","users"); 
		return "index";
	}

	@PostMapping("/users") 
	public String updateUser(@RequestParam (required = false) String userName, @RequestParam (required = false) String name, @RequestParam (required = false) String firstName, @RequestParam (required = false) String address, @RequestParam (required = false) String postalCode, @RequestParam (required = false) String town, @RequestParam (required = false) String secret, @RequestParam (required = false) String userRole, @RequestParam (required = false) Boolean delete, Model model, RedirectAttributes rm){

		if(delete==null) // avoid error Cannot invoke "java.lang.Boolean.booleanValue()" because "delete" is null
		{
			delete=false;
		}
		if(delete)
		{
			if(userName.equals(userDetails.getUsername())) // Admin cannot delete his own account
			{
				model.addAttribute("content", "users");
				rm.addFlashAttribute("message","You cannot delete your own account");
				return "redirect:users";
			}
			else
			{
				if(userName.equals("admin@thescope.site") || userName.equals("admin@thescope.com"))
				{
					model.addAttribute("content", "users");
					rm.addFlashAttribute("message","You cannot delete the primary admin account");
					return "redirect:users";
				}
				else
				{
					if(userService.hasBookings(userService.findUserByUsername(userName)))
					{
						model.addAttribute("content", "users");
						rm.addFlashAttribute("message","User not deleted because user has one or more bookings");
						return "redirect:users";
					}
					else
					{
						userService.deleteUser(userName);
						model.addAttribute("content", "users");
						rm.addFlashAttribute("message","User deleted");
						return "redirect:users";
					}
				}
			}
		}
		else
		{
			if(!name.equals("") && !firstName.equals("") && !address.equals("") && !postalCode.equals("") && !town.equals(""))
			{
				if(userName.equals("admin@thescope.site") && !userRole.equals("Admin") || userName.equals("admin@thescope.com") && !userRole.equals("Admin")) {

					model.addAttribute("content", "users");
					rm.addFlashAttribute("message","You cannot change the role of the primary admin account");
					return "redirect:users";
				}
				else
				{
					// Update user
					userService.updateUser(userName, name, firstName, address, postalCode, town, secret, userRole);
					model.addAttribute("content", "users");
					rm.addFlashAttribute("message","Information succesfully updated");
					return "redirect:users";
				}
			}
			else
			{
				// Check if all fields are filled in
				model.addAttribute("content", "users");
				rm.addFlashAttribute("message","Fill in all fields");
				return "redirect:users";
			}
		}
	}

	@GetMapping("/adduser") // get request
	public String loginGet(Model model) {

		model.addAttribute("content", "adduser");
		return "index";
	}

	@PostMapping("/adduser") 
	public String createUser(@RequestParam (required = false) String username, @RequestParam (required = false) String password, @RequestParam (required = false) String confirmpassword, @RequestParam (required = false) String name, @RequestParam (required = false) String firstname, @RequestParam (required = false) String address, @RequestParam (required = false) String postalcode, @RequestParam (required = false) String town, @RequestParam long role, Model model, RedirectAttributes rm){
		//username = email
		if(!username.equals("") && !password.equals("") && !confirmpassword.equals("") && !name.equals("") && !firstname.equals("") && !address.equals("") && !town.equals(""))
		{
			if(!userService.userExist(username))
			{
				if(password.equals(confirmpassword))
				{
					// Register user
					userService.createUser(username, password, name, firstname, address, postalcode, town, role);

					model.addAttribute("content", "adduser");
					rm.addFlashAttribute("message","User succesfully added");
					return "redirect:adduser";
				}
				else
				{
					// Check if password boxes are the same
					model.addAttribute("content", "adduser");
					rm.addFlashAttribute("message","Confirmation password not the same");
					return "redirect:adduser";
				}
			}
			else
			{
				// Check if all fields are filled in
				model.addAttribute("content", "adduser");
				rm.addFlashAttribute("message","Username (email) already taken");
				return "redirect:adduser";
			}
		}
		else
		{
			// Check if all fields are filled in
			model.addAttribute("content", "adduser");
			rm.addFlashAttribute("message","Fill in all fields");
			return "redirect:adduser";
		}
	}

	// Admin can search for users
	@PostMapping("/users/find") 
	public String findUser(@RequestParam (required = false) String findUserName, @RequestParam (required = false) String findFirstName, @RequestParam (required = false) String findName, @RequestParam (required = false) String roleName, Model model, RedirectAttributes rm){

		this.findUserName=findUserName;
		this.findFirstName=findFirstName;
		this.findName=findName;
		this.roleName=roleName;
		List<User> foundUsers = userService.findUsers(findUserName, findName, findFirstName, roleName);
		List<UserRole> roles = userService.userRoles(); // List is used for dropdown select box
		model.addAttribute("content", "users"); 
		model.addAttribute("roles",roles);  // map content to html elements
		model.addAttribute("users",foundUsers);  // map content to html elements
		return "index";
	}

}
