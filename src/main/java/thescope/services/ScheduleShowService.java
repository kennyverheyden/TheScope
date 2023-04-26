package thescope.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import thescope.exceptions.EntityNotFoundException;
import thescope.models.ScheduleShow;
import thescope.repositories.ScheduleShowRepository;

import java.util.Optional;

@Service
@Transactional
public class ScheduleShowService {

	
	@Autowired
	private  ScheduleShowRepository scheduleShowRepository;
	
	public ScheduleShowService() {}

	public ScheduleShow findScheduleShowById(long id) {
		Optional<ScheduleShow> entity = scheduleShowRepository.findById(id);
		return unwrapScheduleShow(entity, id);
	}
	public void addScheduleShow(ScheduleShow scheduleShow) {
		scheduleShowRepository.save(scheduleShow);
	}

	public static ScheduleShow unwrapScheduleShow(Optional<ScheduleShow> entity, Long id) {
		if (entity.isPresent()) {
			return entity.get();
		} else {
			throw new EntityNotFoundException(id, ScheduleShow.class);
		}
	}
}
