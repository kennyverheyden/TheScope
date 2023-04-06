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

	private final EntityManager entityManager;
	private final ScheduleShowRepository scheduleShowRepository;
	
	@Autowired
	public ScheduleShowService(EntityManager entityManager, ScheduleShowRepository scheduleShowRepository) {
		this.entityManager = entityManager;
		this.scheduleShowRepository = scheduleShowRepository;
	}
	
	public ScheduleShow findScheduleShowById(long id) {
		ScheduleShow scheduleShow = entityManager.find(ScheduleShow.class, id);
		return scheduleShow;
	}
	
	
}
