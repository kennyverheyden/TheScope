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
	private ScheduleShowService scheduleShowService;

	@Test
	public void findScheduleShowTest() {
		ScheduleShow showtest = scheduleShowService.findScheduleShowById(1);
		assertEquals(95, showtest.getMovie().getLength());
	}

	@Test
	public void formatDateTest() {
		ScheduleShow show = scheduleShowService.findScheduleShowById(1);
		System.out.println("DATE/TIME TEST:\nDate:" + show.getFormattedDate() + "\nTime: " + show.getFormattedTime());
	}

}
