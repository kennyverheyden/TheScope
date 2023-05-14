package thescope.processors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LoginProcessor {

	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;

	public boolean login(String userName, String secret) throws Exception
	{
		Authentication auth = new UsernamePasswordAuthenticationToken(userName,secret);
		AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
		auth= authenticationManager.authenticate(auth);
		SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(auth);
		return auth.isAuthenticated();
	}


}