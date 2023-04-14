package thescope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class ,R2dbcAutoConfiguration.class}) // Ignore login screen from Spring Security

public class TheScopeApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx=
		 SpringApplication.run(TheScopeApplication.class, args);

		 
	}

	/**
	 * Deze klasse clean laten, het enige wat hier moet instaan is app.run(). Indien je wilt testen, doe dit via JUnit
	 * of integratietesten.
 	 */

}
