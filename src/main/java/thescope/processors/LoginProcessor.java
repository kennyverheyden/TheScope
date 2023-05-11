package thescope.processors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import thescope.models.User;
import thescope.repositories.UserRepository;
import thescope.services.UserService;

@Component
@RequestScope
public class LoginProcessor {


	@Autowired
	private UserService userService;

	private  PasswordEncoder passwordEncoder;

	public LoginProcessor()
	{
		this.passwordEncoder =  new BCryptPasswordEncoder();
	}


	public boolean login(String userName, String secret)
	{
		// Check if username and password exist
		User user = userService.findUserByUsername(userName);
		if(user==null)
		{
			return false;
		}
		else
		{

			if(user.getUserName().equals(userName) && passwordEncoder.matches(secret,user.getSecret()))
			{
				// Shown on logged welcome page
				secret=null;
				userService.setUserName(userName);
				userService.setName(user.getName());
				userService.setFirstname(user.getFirstName());
				userService.setUserRole(user.getUserRole().getRoleName());
				userService.setRoleID(user.getUserRole().getPKuserRole());
				userService.setSecret(true);
				return true;
			}
			else
			{
				return false;
			}
		}

	}


}