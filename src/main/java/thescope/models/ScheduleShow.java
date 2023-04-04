package thescope.models;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity(name="tblScheduleShow")
public class ScheduleShow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long PKscheduleShow;
	
	@OneToOne
	Movie movie;
	
	@OneToOne
	TheaterRoom room;
	
	@Column(name="time")
	LocalTime time;
	
	@Column(name="date")
	LocalDate date;
	
	public ScheduleShow(Movie movie, TheaterRoom room, LocalTime time, LocalDate date)
	{
		this.movie=movie;
		this.room=room;
		this.time=time;
		this.date=date;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public TheaterRoom getRoom() {
		return room;
	}

	public void setRoom(TheaterRoom room) {
		this.room = room;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}	
}
