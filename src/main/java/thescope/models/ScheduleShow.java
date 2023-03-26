package thescope.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="tblScheduleShow")
public class ScheduleShow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long PKscheduleShow;
	
	String movie;
	String room;
	
	@Column(name="time")
	String time;
	
	@Column(name="date")
	String date;
	
	public ScheduleShow(String movie, String room, String time, String date)
	{
		this.movie=movie;
		this.room=room;
		this.time=time;
		this.date=date;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
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
}
