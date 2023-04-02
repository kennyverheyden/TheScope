package thescope.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import thescope.models.UserRole;
import thescope.repositories.UserRoleRepository;


@RestController
public class UserRoleController {

	private final UserRoleRepository userRoleRepository;

	@Autowired
	public UserRoleController(UserRoleRepository userRoleRepository) {
		this.userRoleRepository = userRoleRepository;
	}

}

