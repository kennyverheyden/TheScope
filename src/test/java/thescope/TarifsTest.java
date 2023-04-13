package thescope;

import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import thescope.models.Tarifs;
import thescope.services.TarifsService;

@SpringBootTest
public class TarifsTest {
	
	TarifsService tarifsService;

	@Test
	public void findTarifsTest() {
		Tarifs testTarifs = tarifsService.findTarifsById(2);
		assertEquals(14, testTarifs.getPriceTaxIncl());
	}
}
