package thescope.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thescope.models.TheaterRoom;
import thescope.repositories.TheaterRoomRepository;

import java.util.List;

@Service
@Transactional
public class TheaterRoomService {

    @Autowired
    private  TheaterRoomRepository theaterRoomRepository;
    
    public TheaterRoomService() {}
  
    
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
