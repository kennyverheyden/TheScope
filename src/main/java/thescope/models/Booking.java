package thescope.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="tblBookings")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long PKbooking;
	
	@ManyToOne
	@JoinColumn(name= "FKUser")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="FKScheduleShow")
	private ScheduleShow scheduleShow;
	
	public Booking(){}
	
	public Booking(User user, ScheduleShow scheduleShow) {
		this.user= user;
		this.scheduleShow= scheduleShow;
	}
	
	


	
}
