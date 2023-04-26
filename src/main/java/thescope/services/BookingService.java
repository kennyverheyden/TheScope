package thescope.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thescope.exceptions.EntityNotFoundException;
import thescope.models.Booking;
import thescope.models.User;
import thescope.repositories.BookingRepository;
import thescope.repositories.UserRepository;


@Service
@Transactional
public class BookingService {

	@Autowired
   private BookingRepository bookingRepository;
	@Autowired
	EntityManager em;
	
	@Autowired
	private UserRepository userRepository;

   public BookingService() {}
	
    
    public Booking findBookingById(long id) {
	   Optional<Booking> entity = bookingRepository.findById(id);
	   Booking booking = unwrapBooking(entity, id);
	   return booking;
    }
    public void addBooking(Booking booking) {
    	bookingRepository.save(booking);
    }
    public Booking findBookingByUser(String name) {
    	Query query= em.createQuery("SELECT b FROM Booking b "
    			+ "JOIN User u ON b.user = u.PKuser "
    			+ "WHERE u.name LIKE ?1 "
    			+ "ORDER BY b.PKbooking desc ");
    	query.setParameter(1, name);
    	List<Booking> bookings= query.getResultList();
    	return bookings.get(0);
    }

	public static Booking unwrapBooking(Optional<Booking> entity, Long id) {
	   if (entity.isPresent()) {
		   return entity.get();
	   } else {
		   throw new EntityNotFoundException(id, Booking.class);
	   }
	}
}
