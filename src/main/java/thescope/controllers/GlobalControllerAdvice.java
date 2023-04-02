package thescope.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import thescope.services.UserService;

@ControllerAdvice
public class GlobalControllerAdvice {


	/*****************************************************************************
	 * 
	// This controller is used for determining the user role authentication
	 * 
	 ******************************************************************************/


	private final UserService userService;

	@Autowired
	public GlobalControllerAdvice(UserService userService)
	{
		this.userService=userService;
	}

	@ModelAttribute("firstNameINmenu")
	public String firstNameINmenu()
	{
		return userService.getFirstname();
	}

	@ModelAttribute("isLoggedIn")
	public boolean isLoggedIn()
	{

		if(userService.getUserName()!=null && userService.getSecret()!=null && userService.getUserRole()!=null)
		{
			return true;
		}
		else
		{
			// User is not logged in
			return false;
		}
	}

	@ModelAttribute("isStaff")
	public boolean isStaff() {

		// Check if user is logged in, to avoid error
		if(userService.getUserRole()!=null)
		{
			// Check if user is staff member
			return userService.isStaff();
		}
		else
		{
			// User is not logged in
			return false;
		}
	}

	@ModelAttribute("isCustomer")
	public boolean isCustomer() {

		// Check if user is logged in, to avoid error
		if(userService.getUserRole()!=null)
		{
			// Check if user is customer member
			return userService.isCustomer();
		}
		else
		{
			// User is not logged in
			return false;
		}
	}

	@ModelAttribute("isAdmin")
	public boolean isAdmin() {

		// Check if user is logged in, to avoid error
		if(userService.getUserRole()!=null)
		{
			// Check if user is customer member
			return userService.isAdmin();
		}
		else
		{
			// User is not logged in
			return false;
		}
	}

	@ModelAttribute("isCleaning")
	public boolean isCleaning() {

		// Check if user is logged in, to avoid error
		if(userService.getUserRole()!=null)
		{
			// Check if user is customer member
			return userService.isCleaning();
		}
		else
		{
			// User is not logged in
			return false;
		}
	}

	@ModelAttribute("isDesk")
	public boolean isDesk() {

		// Check if user is logged in, to avoid error
		if(userService.getUserRole()!=null)
		{
			// Check if user is customer member
			return userService.isDesk();
		}
		else
		{
			// User is not logged in
			return false;
		}
	}

	@ModelAttribute("isShop")
	public boolean isShop() {

		// Check if user is logged in, to avoid error
		if(userService.getUserRole()!=null)
		{
			// Check if user is customer member
			return userService.isShop();
		}
		else
		{
			// User is not logged in
			return false;
		}
	}
}

