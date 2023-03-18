package thescope.models;

public class User {
	int userID;
	String name;
	String firstName;
	String address;
	int postalCode;
	String town;


	/**contructor**/

	public User(int userID, String name, String firstName, String address, int postalCode, String town) {
		this.userID = userID;
		this.name = name;
		this.firstName = firstName;
		this.address = address;
		this.postalCode = postalCode;
		this.town = town;
	}

	/**get&set**/
	
	public int getUserID() {
		return userID;
	}


	public void setUserId(int userID) {
		this.userID = userID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}


	public String getTown() {
		return town;
	}


	public void setTown(String town) {
		this.town = town;
	}


}
