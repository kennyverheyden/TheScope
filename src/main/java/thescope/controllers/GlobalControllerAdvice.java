package thescope.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import thescope.processors.UserDetailsImpl;
import thescope.services.UserService;

@ControllerAdvice
public class GlobalControllerAdvice {

	/*****************************************************************************
	 * 
	// This controller is used for determining the user role authentication
	 * 
	 ******************************************************************************/

	public GlobalControllerAdvice() {}

	@Autowired
	private UserDetailsImpl userDetails;

	// Set logged in user firstname in the menu for the main page
	@ModelAttribute("firstNameINmenu")
	public String firstNameINmenu()
	{
		if(userDetails.getUser()!=null)
		{
			return userDetails.getUser().getFirstName();
		}
		return null;
	}

	// Check loggedIn status
	@ModelAttribute("isLoggedIn")
	public boolean isLoggedIn()
	{
		if(userDetails.getUser()!=null)
		{
			return true;
		}
		else
		{
			// User is not logged in
			return false;
		}
	}

	@ModelAttribute("isShop")
	public boolean isShop() {


		if(userDetails.getUser()!=null)
		{
			if(userDetails.getUser().getUserRole().getPKuserRole()==1) {
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			// User is not logged in
			return false;
		}
	}

	@ModelAttribute("isStaff")
	public boolean isStaff() {

		if(userDetails.getUser()!=null)
		{
			if(userDetails.getUser().getUserRole().getPKuserRole()==1 ||
					userDetails.getUser().getUserRole().getPKuserRole()==2 ||
					userDetails.getUser().getUserRole().getPKuserRole()==3 ||
					userDetails.getUser().getUserRole().getPKuserRole()==4) {
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			// User is not logged in
			return false;
		}
	}

	@ModelAttribute("isDesk")
	public boolean isDesk() {

		if(userDetails.getUser()!=null)
		{
			if(userDetails.getUser().getUserRole().getPKuserRole()==2) {
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	@ModelAttribute("isCleaning")
	public boolean isCleaning() {

		if(userDetails.getUser()!=null)
		{
			if(userDetails.getUser().getUserRole().getPKuserRole()==3) {
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			// User is not logged in
			return false;
		}
	}

	@ModelAttribute("isAdmin")
	public boolean isAdmin() {

		if(userDetails.getUser()!=null)
		{
			if(userDetails.getUser().getUserRole().getPKuserRole()==4) {
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			// User is not logged in
			return false;
		}
	}

	@ModelAttribute("isCustomer")
	public boolean isCustomer() {

		if(userDetails.getUser()!=null)
		{
			if(userDetails.getUser().getUserRole().getPKuserRole()==5) {
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			// User is not logged in
			return false;
		}
	}

}

