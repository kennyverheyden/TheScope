package thescope.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    	return bookingRepository.findById(id).get();
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
}
