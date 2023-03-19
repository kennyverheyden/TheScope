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
		userService.setUsername(username);
		userService.setSecret(secret);
		// Check if username and password exist
		for(int i=0;i<userRepository.selectUsers().size();i++)
		{
			if(userRepository.selectUsers().get(i).getUserID().equals(userService.getUsername()) && userRepository.selectUsers().get(i).getSecret().equals(userService.getSecret()))
			{
				// Shown on logged welcome page
				userService.setName(userRepository.selectUsers().get(i).getName());
				userService.setFirstname(userRepository.selectUsers().get(i).getFirstName());
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