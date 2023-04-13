package thescope.services;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import thescope.models.Booking;
import thescope.repositories.BookingRepository;

import java.util.List;

@Service
@Transactional
public class BookingService {

	@Autowired
   private BookingRepository bookingRepository;

   public BookingService() {}
	
    public BookingService(BookingRepository bookingRepository) {
       this.bookingRepository = bookingRepository;
    }
    
    public Booking findBookingById(long id) {
    	return bookingRepository.findById(id).get();
    }
}
