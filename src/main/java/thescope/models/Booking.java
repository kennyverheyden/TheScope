package thescope.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="tblBookings")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long PKbooking;
	
	@Column(name="FKuserRole")
	String name;
	String firstname;;
	String movie;
	String time;
	String date;
	String location;
	
	public Booking()
	{
		
	}
	
	public Booking(String name, String firstname, String movie, String time, String date, String location)
	{
		this.name=name;
		this.firstname=firstname;
		this.movie=movie;
		this.time=time;
		this.date=date;
		this.location=location;
	}


	
}
