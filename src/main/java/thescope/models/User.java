package thescope.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="tblUsers")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int PKuser;
	@Column(name="FKuserRole")
	int role;
	@Column(name="eMailuserID")
	String userID; //ex. verheydenk
	@Column(name="secret")
	String secret;
	@Column(name="name")
	String name;
	@Column(name="firstname")
	String firstName;
	@Column(name="address")
	String address;
	@Column(name="postalCode")
	String postalCode;
	@Column(name="town")
	String town;


	/**contructor**/

	public User()
	{

	}

	public User(String userID, String secret, String name, String firstName, String address, String postalCode, String town, int role) {
		this.userID = userID; //ex. verheydenk
		this.secret = secret;
		this.name = name;
		this.firstName = firstName;
		this.address = address;
		this.postalCode = postalCode;
		this.town = town;
		this.role =role;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

}
