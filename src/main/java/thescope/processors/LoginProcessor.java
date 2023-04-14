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

	private String userName;
	private String secret;

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	private  PasswordEncoder passwordEncoder;
	
	public LoginProcessor()
	{
		this.passwordEncoder =  new BCryptPasswordEncoder();
	}
	

	public boolean login()
	{
		// Session scope bean, username must be available
		userService.setUserName(userName);
		userService.setSecret(secret);
		
		// Check if username and password exist
		for(User user:userService.list())
		{
			if(user.getUserName().equals(userService.getUserName()) && passwordEncoder.matches(userService.getSecret(),user.getSecret()))
			{
				// Shown on logged welcome page
				userService.setName(user.getName());
				userService.setFirstname(user.getFirstName());
				userService.setUserRole(user.getUserRole().getRoleName());
				userService.setRoleID(user.getUserRole().getPKuserRole());
				return true;
			}
		}
		return false;
	}

	public void setUserName(String userName)
	{
		this.userName=userName;
	}

	public void setSecret(String secret)
	{
		this.secret=secret;
	}

}