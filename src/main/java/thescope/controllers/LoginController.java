package thescope.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import thescope.processors.LoginProcessor;

@Controller
public class LoginController {

	// Injector login
	private final LoginProcessor loginProcessor;
	@Autowired
	public LoginController(LoginProcessor loginProcessor)
	{
		this.loginProcessor=loginProcessor;
	}


	@GetMapping("/login") // get request
	public String loginGet(Model model) {
		model.addAttribute("content", "login");
		return "index";
	}

	@PostMapping("/login") 
	public String loginPost(@RequestParam String username, @RequestParam String password, Model model, RedirectAttributes rm) {
		boolean loggedIn = false;

		  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	     String hashedPass = bCryptPasswordEncoder.encode(password);

		
		loginProcessor.setUserName(username);
		loginProcessor.setSecret(hashedPass);
		System.out.println(hashedPass);

		loggedIn = loginProcessor.login();

		if(loggedIn == true)
		{
			model.addAttribute("content", "main");
			return "redirect:main";
		}
		else
		{

			model.addAttribute("content", "login");
			rm.addFlashAttribute("message","Login failed");
			return "redirect:login";
		}


	}

}
