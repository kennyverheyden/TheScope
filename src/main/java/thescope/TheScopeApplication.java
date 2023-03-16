package thescope;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import thescope.models.UserRole;

@SpringBootApplication
public class TheScopeApplication implements CommandLineRunner {

	//Database connection
	//Spring Boot will automatically wire this object using application.properties
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(TheScopeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Read records:
		List<UserRole> userRoles = jdbcTemplate.query("SELECT roleName FROM tblUserRole",
				(resultSet, rowNum) -> new UserRole(resultSet.getString("roleName")));
		//Print read records:
		userRoles.forEach(System.out::println);
	}

}
