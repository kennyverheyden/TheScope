package thescope;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import thescope.models.ScheduleShow;
import thescope.services.ScheduleShowService;

@SpringBootTest
public class ScheduleShowTest {

	@Autowired
	private ScheduleShowService sss;
	
	@Test
	public void contextLoads() {
		System.out.println("Test successfully loaded");
	}	
	@Test
	public void scheduleshowLoadedTest() {
	ScheduleShow showtest= sss.findScheduleShowById(1);
	assertEquals(95, showtest.getMovie().getLength());
	}
}
