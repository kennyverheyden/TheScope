package thescope.processors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import thescope.repositories.UserRepository;
import thescope.services.UserService;

@Component
@RequestScope
public class LoginProcessor {

	private String userName;
	private String secret;

	private final UserService userService;
	private final UserRepository userRepository;
	private  PasswordEncoder passwordEncoder;
	
	public LoginProcessor(UserService userService, UserRepository userRepository)
	{
		this.userService=userService;
		this.userRepository=userRepository;
		this.passwordEncoder =  new BCryptPasswordEncoder();
	}

	public boolean login()
	{
		// Session scope bean, username must be available
		userService.setUserName(userName);
		userService.setSecret(secret);
		
		// Check if username and password exist
		for(int i=0;i<userRepository.findAll().size();i++)
		{
			if(userRepository.findAll().get(i).getUserName().equals(userService.getUserName()) && passwordEncoder.matches(userService.getSecret(),userRepository.findAll().get(i).getSecret()))
			{
				// Shown on logged welcome page
				userService.setName(userRepository.findAll().get(i).getName());
				userService.setFirstname(userRepository.findAll().get(i).getFirstName());
				userService.setUserRole(userRepository.findAll().get(i).getUserRole().getRoleName());
				userService.setRoleID(userRepository.findAll().get(i).getUserRole().getPKuserRole());
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