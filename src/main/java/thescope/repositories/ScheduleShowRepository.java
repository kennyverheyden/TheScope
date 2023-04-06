package thescope.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thescope.models.ScheduleShow;

@Repository
public interface ScheduleShowRepository extends JpaRepository<ScheduleShow,Long>{
	

}

