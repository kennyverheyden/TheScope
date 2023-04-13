package thescope.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import thescope.repositories.UserRoleRepository;
import thescope.services.UserService;

@RestController
public class UserRoleController {
	@Autowired
	private UserService userService;

	public UserRoleController(){

	}

}

