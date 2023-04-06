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

    private final EntityManager entityManager;
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(EntityManager entityManager, BookingRepository bookingRepository) {
        this.entityManager = entityManager;
        this.bookingRepository = bookingRepository;
    }
    
    public Booking findBookingById(long id) {
    	return bookingRepository.findById(id).get();
    }
}
