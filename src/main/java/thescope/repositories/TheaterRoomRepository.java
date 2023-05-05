package thescope.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thescope.models.TheaterRoom;

@Repository
public interface TheaterRoomRepository extends JpaRepository<TheaterRoom,Long>{	

}
