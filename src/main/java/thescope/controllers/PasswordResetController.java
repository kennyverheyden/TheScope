package thescope.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import thescope.models.User;
import thescope.repositories.UserRepository;
import thescope.services.UserService;

@Controller
public class PasswordResetController {

	private final UserRepository userRepository;
	private final UserService userService;

	@Autowired
	public PasswordResetController(UserRepository userRepository, UserService userService) {
		this.userRepository = userRepository;
		this.userService=userService;
	}

	@GetMapping("/passwordreset") // get request
	public String selectGet(Model model) {

		// When user is not logged on, the String is null
		if(userService.getUserName()==null)
		{
			model.addAttribute("content", "login");
			return "index";
		}

		User user = userService.findUserByUsername(userService.getUserName());
		model.addAttribute("userName",user.getUserName());  // map content to html elements
		model.addAttribute("content", "passwordreset"); 

		return "index";
	}

	@PostMapping("/passwordreset") 
	public String commentPost(@RequestParam (required = false) String userName, @RequestParam (required = false) String password, @RequestParam (required = false) String confirmPassword, Model model, RedirectAttributes rm){

		if(!password.equals("") && !confirmPassword.equals(""))
		{
			if(password.equals(confirmPassword))
			{
				userService.updatePassword(userName, password);
				model.addAttribute("content", "passwordreset");
				rm.addFlashAttribute("message","Password succesfully changed");
				return "redirect:passwordreset";
			}
			else
			{
				model.addAttribute("content", "passwordreset");
				rm.addFlashAttribute("message","Password not the same as confirmation password");
				return "redirect:passwordreset";
			}
		}
		else
		{
			// Check if all fields are filled in
			model.addAttribute("content", "passwordreset");
			rm.addFlashAttribute("message","Fill in all fields");
			return "redirect:passwordreset";
		}

	}

}