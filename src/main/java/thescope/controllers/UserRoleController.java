package thescope.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import thescope.repositories.UserRoleRepository;

@RestController
public class UserRoleController {

	@Autowired
	private UserRoleRepository userRoleRepository;

	public UserRoleController() {}
			
}

