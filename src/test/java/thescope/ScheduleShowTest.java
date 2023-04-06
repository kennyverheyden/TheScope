package thescope;

import static org.junit.Assert.assertEquals;

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
	private void testFindScheduleById() {
		// TODO Auto-generated method stub
		ScheduleShow schedule = sss.findScheduleShowById(1);
		System.out.println(schedule);
		assertEquals(1, schedule.getPKscheduleShow());

	}
	
	
	
	
	
	
}
