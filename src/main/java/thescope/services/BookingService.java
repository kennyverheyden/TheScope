package thescope.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import thescope.exceptions.BookingNotFoundException;
import thescope.models.Booking;
import thescope.repositories.BookingRepository;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public Booking findBookingById(Long id) {
        Optional<Booking> entity = bookingRepository.findById(id);
        Booking unwrappedBooking = unwrapBooking(entity, id);
    	return unwrappedBooking;
    }

    public List<Booking> findBookingsByUserId(Long id) {                    //zoeken op basis van fkUser
        List<Booking> bookings = bookingRepository.findBookingsByUser(id);
        return bookings;
    }

    public static Booking unwrapBooking(Optional<Booking> entity, Long id) {   //optional unwrapping om exceptions op te vangen
        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new BookingNotFoundException(id);
        }
    }

}
