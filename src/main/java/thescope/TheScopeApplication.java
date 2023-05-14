package thescope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;


@EnableMethodSecurity(securedEnabled=true) // Method security
@EnableGlobalAuthentication
@SpringBootApplication()
public class TheScopeApplication {

	public static void main(String[] args) {
		 SpringApplication.run(TheScopeApplication.class, args);
	}


}
