package thescope.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import thescope.models.ScheduleShow;
import thescope.repositories.ScheduleRepository;

@Service
@Transactional
public class ScheduleShowService {

	private final EntityManager entityManager;
	private final ScheduleRepository scheduleRepository;
	
	@Autowired
	public ScheduleShowService(EntityManager entityManager, ScheduleRepository scheduleRepository) {
		this.entityManager = entityManager;
		this.scheduleRepository = scheduleRepository;
	}
	
	
}
