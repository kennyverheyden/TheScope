package thescope.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import thescope.models.ScheduleShow;
import thescope.repositories.ScheduleShowRepository;

import java.util.Optional;

@Service
@Transactional
public class ScheduleShowService {
	@Autowired
	private ScheduleShowRepository scheduleShowRepository;

	public ScheduleShow findScheduleShowById(long id) {
		Optional<ScheduleShow> entity = scheduleShowRepository.findById(id);
		ScheduleShow unwrappedScheduleShow = unwrapScheduleShow(entity, id);
		return unwrappedScheduleShow;
	}
	public void addScheduleShow(ScheduleShow scheduleShow) {
		scheduleShowRepository.save(scheduleShow);
	}

	public static ScheduleShow unwrapScheduleShow(Optional<ScheduleShow> entity, Long id) {
		if (entity.isPresent()) {
			return entity.get();
		} else {
			throw new RuntimeException();
		}
	}
	
}
