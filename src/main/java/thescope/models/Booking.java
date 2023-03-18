package thescope.models;

public class Booking {

	String name;
	String firstname;;
	String movie;
	String time;
	String date;
	String location;
	
	public Booking(String name, String firstname, String movie, String time, String date, String location)
	{
		this.name=name;
		this.firstname=firstname;
		this.movie=movie;
		this.time=time;
		this.date=date;
		this.location=location;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
