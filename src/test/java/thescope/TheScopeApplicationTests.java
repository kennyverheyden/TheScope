package thescope;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import thescope.controllers.MainController;
import thescope.repositories.UserRepository;
import thescope.services.UserService;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class TheScopeApplicationTests {

	@Test
	void contextLoads(ApplicationContext context) {
		assertNotNull(context); //springContext load successful?
	}

}
