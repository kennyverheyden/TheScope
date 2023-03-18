package thescope.models;

public class TheaterRoom {

    int numSeats;
    int numVipSeats;
    String location;

    /**construct**/
    public TheaterRoom(int numSeats, int numVipSeats, String location) {
        this.numSeats = numSeats;
        this.numVipSeats = numVipSeats;
        this.location = location;
    }

    /**get&set**/

    public int getNumSeats() {
        return numSeats;
    }

    public void setNumSeats(int numSeats) {
        this.numSeats = numSeats;
    }

    public int getNumVipSeats() {
        return numVipSeats;
    }

    public void setNumVipSeats(int numVipSeats) {
        this.numVipSeats = numVipSeats;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
