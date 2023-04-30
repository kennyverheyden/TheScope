package thescope.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thescope.exceptions.EntityNotFoundException;
import thescope.models.Booking;
import thescope.models.ScheduleShow;
import thescope.models.User;
import thescope.repositories.BookingRepository;


@Service
@Transactional
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	EntityManager em;
	
	private Long bookedSchedule; // Keep 
	private String bookingStatus; // Shows message to the customer to login or to create an account

	public BookingService() {}

	public List<Booking> findAll()
	{
		return bookingRepository.findAll();
	} 

	public List<Booking> findByUser(User user)
	{
		List<Booking> allBookings = bookingRepository.findAll();
		List<Booking> bookings= new ArrayList();
		for(Booking booking:allBookings)
		{
			if(booking.getUser().getPKuser()==user.getPKuser())
			{
				bookings.add(booking);
			}
		}
		return bookings;
	}

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

	// Check if schedule has booking before delete
	public boolean bookingHasSchedule(ScheduleShow schedule)
	{
		List<Booking> bookings = this.findAll();
		for(Booking booking:bookings)
		{
			if(booking.getScheduleShow().getPKscheduleShow()==schedule.getPKscheduleShow())
			{
				return true;
			}
		}
		return false;
	}

	public Long getBookedSchedule() {
		return bookedSchedule;
	}

	public void setBookedSchedule(Long bookedSchedule) {
		this.bookedSchedule = bookedSchedule;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

}
