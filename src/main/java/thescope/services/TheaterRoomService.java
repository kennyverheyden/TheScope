package thescope.services;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thescope.exceptions.EntityNotFoundException;
import thescope.models.Movie;
import thescope.models.TheaterRoom;
import thescope.repositories.MovieRepository;
import thescope.repositories.TheaterRoomRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TheaterRoomService {

    @Autowired
    private TheaterRoomRepository theaterRoomRepository;

    public TheaterRoom findTheatherRoomById(Long id) {
        Optional<TheaterRoom> entity = theaterRoomRepository.findById(id);
        TheaterRoom unwrappedTheatherRoom = unwrapTheaterRoom(entity, id);
        return unwrappedTheatherRoom;
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

    public static TheaterRoom unwrapTheaterRoom(Optional<TheaterRoom> entity, Long id) {
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new EntityNotFoundException(id, TheaterRoom.class);
        }
    }

}
