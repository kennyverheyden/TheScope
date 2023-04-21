package thescope.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thescope.models.Booking;
import thescope.models.User;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long>{
	

}
