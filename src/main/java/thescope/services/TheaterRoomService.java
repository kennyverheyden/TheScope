package thescope.services;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thescope.models.Movie;
import thescope.models.TheaterRoom;
import thescope.repositories.MovieRepository;
import thescope.repositories.TheaterRoomRepository;

import java.util.List;

@Service
@Transactional
public class TheaterRoomService {

    private final EntityManager entityManager;
    private final TheaterRoomRepository theaterRoomRepository;
    
    @Autowired
    public TheaterRoomService(EntityManager entityManager, TheaterRoomRepository theaterRoomRepository) {
        this.entityManager = entityManager;
        this.theaterRoomRepository = theaterRoomRepository;
    }


    public TheaterRoom findTheatherRoomById(Long id) {
        return entityManager.find(TheaterRoom.class, id);
    }

   

    public List<TheaterRoom> findAllTheaterRooms() {
        List<TheaterRoom> rooms = theaterRoomRepository.findAll();
        return rooms;
    }

    public void addTheaterRoom(TheaterRoom theaterRoom) {
        entityManager.persist(theaterRoom);
    }

    public void deleteTheaterRoomById(Long id) {
        entityManager.remove(entityManager.find(TheaterRoom.class, id));
    }

}
