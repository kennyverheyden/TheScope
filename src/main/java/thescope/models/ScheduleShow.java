package thescope.models;

import java.text.SimpleDateFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "tblScheduleShow")
public class ScheduleShow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long PKscheduleShow;

	@ManyToOne
	@JoinColumn(name = "FKMovie")
	private Movie movie;

	@ManyToOne
	@JoinColumn(name = "FKtheaterRoom")
	private TheaterRoom theaterRoom;

	@Column(name = "time")
	String time;

	@Column(name = "date")
	String date;

	@Column(name = "countSeats")
	int countSeats;

	@Column(name = "countVipSeats")
	int countVipSeats;

	// Constructors
	public ScheduleShow() {
		this.countSeats = 0;
		this.countVipSeats = 0;
	}

	public ScheduleShow(Movie movie, TheaterRoom theaterRoom, String time, String date)

	{
		this.movie = movie;
		this.theaterRoom = theaterRoom;
		this.time = time;
		this.date = date;
		this.countSeats = 0;
		this.countVipSeats = 0;
	}

	// Formatted Date & Time
	public String getFormattedDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E dd/MM/yyyy");
		return simpleDateFormat.format(date);
	}

	public String getFormattedTime() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		return simpleDateFormat.format(time);
	}

	// Getters & Setters
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public long getPKscheduleShow() {
		return PKscheduleShow;
	}

	public TheaterRoom getTheaterRoom() {
		return theaterRoom;
	}

	public void setTheaterRoom(TheaterRoom theaterRoom) {
		this.theaterRoom = theaterRoom;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCountSeats() {
		return countSeats;
	}

	public void setCountSeats(int countSeats) {
		this.countSeats = countSeats;
	}

	public int getCountVipSeats() {
		return countVipSeats;
	}

	public void setCountVipSeats(int countVipSeats) {
		this.countVipSeats = countVipSeats;
	}

}
