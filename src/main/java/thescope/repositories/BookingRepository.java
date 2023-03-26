package thescope.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thescope.models.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long>{
	

}
