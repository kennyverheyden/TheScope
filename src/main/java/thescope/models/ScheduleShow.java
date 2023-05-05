package thescope.models;

import java.sql.Date;
import java.sql.Time;
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
	Time time;

	@Column(name = "date")
	Date date;

	@Column(name="countSeats")
	int countSeats;

	@Column(name="countVipSeats")
	int countVipSeats;

	public ScheduleShow() {
		this.countSeats=0;
		this.countVipSeats=0;
	}

	public ScheduleShow(Movie movie, TheaterRoom theaterRoom, Time time, Date date)

	{
		this.movie = movie;
		this.theaterRoom = theaterRoom;
		this.time = time;
		this.date = date;
		this.countSeats=0;
		this.countVipSeats=0;
	}

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

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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

