package thescope.models;

public class CleaningSchedule {

	String name; // tblUser
	String firstname;
	String movie;
	int length; // needed for calculating cleaning time
	String room; // location in DB (tblTheaterRoom)
	String time; // tblScheduleShow
	String date;

	public CleaningSchedule(String name, String firstname, String movie, int length, String location, String time, String date)
	{
		this.name=name;
		this.firstname=firstname;
		this.movie=movie;
		this.length=length;
		this.room=location;
		this.time=time;
		this.date=date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
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
