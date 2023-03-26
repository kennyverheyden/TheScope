package thescope.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thescope.models.CleaningSchedule;

@Repository
public interface CleaningScheduleRepository extends JpaRepository<CleaningSchedule,Long>{
	

}
