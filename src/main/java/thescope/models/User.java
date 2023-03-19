package thescope.models;

public class User {
	String userID; //ex. verheydenk
	String secret;
	String name;
	String firstName;
	String address;
	int postalCode;
	String town;


	/**contructor**/

	public User(String userID, String secret, String name, String firstName, String address, int postalCode, String town) {
		this.userID = userID; //ex. verheydenk
		this.secret = secret;
		this.name = name;
		this.firstName = firstName;
		this.address = address;
		this.postalCode = postalCode;
		this.town = town;
	}

	/**get&set**/
	
	public String getUserID() {
		return userID;
	}

	public void setUserId(String userID) {
		this.userID = userID;
	}
	
	public String getSecret() {
		return secret;
	}
	
	public void setSecret(String secret)
	{
		this.secret=secret;
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
