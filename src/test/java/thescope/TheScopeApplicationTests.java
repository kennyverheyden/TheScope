package thescope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import thescope.controllers.MainController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TheScopeApplicationTests {

	@Autowired
	private MainController mainController;

	@Test
	void contextLoads() {
		assertThat(mainController).isNotNull();		//springContext load successful?
	}

}
