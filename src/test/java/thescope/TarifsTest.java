package thescope;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import thescope.models.Tarifs;
import thescope.services.TarifsService;

@SpringBootTest
public class TarifsTest {

	@Autowired
	TarifsService ts;

	@Test
	public void findTarifsTest() {
		Tarifs testTarifs = ts.findTarifsById(2);
		assertEquals(14, testTarifs.getPriceTaxIncl());
	}

	@Test
	public void addTarifsTest() {
		Tarifs testTarifs = new Tarifs();
		testTarifs.setName("test");
		testTarifs.setPriceTaxIncl(99);
		testTarifs.setActive(true);
		testTarifs.setPriceTaxExcl(97);
		ts.addTarifs(testTarifs);
		assertEquals(4, ts.findAllTarifs().size());
		ts.removeTarifs(testTarifs);
	}

	@Test
	public void testTarifsActive() {

		ts.ChangeTarifsInactive(2);
		assertEquals(false, ts.findTarifsById(2).isActive());
	}
}
