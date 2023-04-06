package thescope.models;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="tblScheduleShow")
public class ScheduleShow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long PKscheduleShow;
	
	@ManyToOne
	@JoinColumn(name= "FKMovie")
	private Movie movie;
	
	@ManyToOne
	@JoinColumn(name="FKtheaterRoom")
	private TheaterRoom theaterRoom;
	
	@Column(name="time")
	Time time;
	
	@Column(name="date")
	Date date;
	
	public ScheduleShow() {}
	
	public ScheduleShow(Movie movie, TheaterRoom theaterRoom, Time time, Date date)
	{
		this.movie=movie;
		this.theaterRoom=theaterRoom;
		this.time=time;
		this.date=date;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
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

	public long getPKscheduleShow() {
		return PKscheduleShow;
	}
	
	
}
