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

    
    @Autowired
    private  TheaterRoomRepository theaterRoomRepository;
    
    public TheaterRoomService() {}
    public TheaterRoomService(TheaterRoomRepository theaterRoomRepository) {
        
        this.theaterRoomRepository = theaterRoomRepository;
    }


    public TheaterRoom findTheatherRoomById(Long id) {
        return theaterRoomRepository.findById(id).get();
    }

   

    public List<TheaterRoom> findAllTheaterRooms() {
        List<TheaterRoom> rooms = theaterRoomRepository.findAll();
        return rooms;
    }

    public void addTheaterRoom(TheaterRoom theaterRoom) {
        theaterRoomRepository.save(theaterRoom);
    }

    public void deleteTheaterRoomById(Long id) {
        theaterRoomRepository.deleteById(id);
    }

}
