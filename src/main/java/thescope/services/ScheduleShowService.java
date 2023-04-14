package thescope.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import thescope.models.ScheduleShow;
import thescope.repositories.ScheduleShowRepository;

@Service
@Transactional
public class ScheduleShowService {

	
	@Autowired
	private  ScheduleShowRepository scheduleShowRepository;
	
	public ScheduleShowService() {}
	
	
	public ScheduleShow findScheduleShowById(long id) {
	
		ScheduleShow scheduleShow = scheduleShowRepository.findById(id).get();
		return scheduleShow;
	}
	public void addScheduleShow(ScheduleShow scheduleShow) {
		scheduleShowRepository.save(scheduleShow);
	}
	
}
