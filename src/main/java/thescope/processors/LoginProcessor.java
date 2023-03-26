package thescope.processors;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import thescope.repositories.UserRepository;
import thescope.services.UserService;

@Component
@RequestScope
public class LoginProcessor {

	private String username;
	private String secret;

	private final UserService userService;
	private final UserRepository userRepository;
	public LoginProcessor(UserService userService, UserRepository userRepository)
	{
		this.userService=userService;
		this.userRepository=userRepository;
	}

	public boolean login()
	{
		// Session scope bean, username must be available
		userService.setUserName(username);
		userService.setSecret(secret);

		// Check if username and password exist
		for(int i=0;i<userRepository.findAll().size();i++)
		{
			if(userRepository.findAll().get(i).getUserID().equals(userService.getUserName()) && userRepository.findAll().get(i).getSecret().equals(userService.getSecret()))
			{
				// Shown on logged welcome page
				userService.setName(userRepository.findAll().get(i).getName());
				userService.setFirstname(userRepository.findAll().get(i).getFirstName());
				return true;
			}
		}
		return false;
	}

	public void setUserName(String username)
	{
		this.username=username;
	}

	public void setSecret(String secret)
	{
		this.secret=secret;
	}

}