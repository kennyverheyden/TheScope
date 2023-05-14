package thescope.processors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests()
		.requestMatchers("/**").permitAll()
		.requestMatchers("/account").authenticated()
		.requestMatchers("/addmovies").authenticated()
		.requestMatchers("/main").authenticated()
		.requestMatchers("/bookingsstaff").authenticated()
		.requestMatchers("/schedule").authenticated()
		.requestMatchers("/editmovies").authenticated()
		.requestMatchers("/cleanings").authenticated()
		.requestMatchers("/rooms").authenticated()
		.requestMatchers("/passwordreset").authenticated()
		.requestMatchers("/booknow").authenticated()
		.requestMatchers("/adduser").hasAnyAuthority("ADMIN")
		.requestMatchers("/users").hasAnyAuthority("ADMIN")
		.and()
		.formLogin()
		.loginPage("/login")
		//.loginProcessingUrl("/login")
		//.defaultSuccessUrl("/main")
		.permitAll()
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/")
		.invalidateHttpSession(true)
		.permitAll()
		.and()
		.csrf()
		.disable();
		return http.build();
	}
  
}
