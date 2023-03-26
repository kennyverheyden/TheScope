package thescope.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="tblTheaterRoom")
public class TheaterRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long PKtheaterRoom;

	@Column(name="maxNormalSeats")
	int maxNormalSeats;

	@Column(name="maxVipSeats")
	int maxVipSeats;

	@Column(name="location")
	String location;

	/**construct**/
	public TheaterRoom(int maxNormalSeats, int maxVipSeats, String location) {
		this.maxNormalSeats = maxNormalSeats;
		this.maxVipSeats = maxVipSeats;
		this.location = location;
	}

	/**get&set**/

	public long getPKtheaterRoom() {
		return PKtheaterRoom;
	}

	public void setPKtheaterRoom(long pKtheaterRoom) {
		PKtheaterRoom = pKtheaterRoom;
	}

	public int getMaxNormalSeats() {
		return maxNormalSeats;
	}

	public void setMaxNormalSeats(int maxNormalSeats) {
		this.maxNormalSeats = maxNormalSeats;
	}

	public int getMaxVipSeats() {
		return maxVipSeats;
	}

	public void setMaxVipSeats(int maxVipSeats) {
		this.maxVipSeats = maxVipSeats;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
