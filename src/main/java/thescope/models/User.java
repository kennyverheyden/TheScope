package thescope.models;

import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name="tblUsers")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int PKuser;
	@OneToOne
	@JoinColumn(name="FKuserRole")
	UserRole userRole;
	@Column(name="eMailuserID")
	String userName; //ex. verheydenk
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

	public User(String userName, String secret, String name, String firstName, String address, String postalCode, String town, UserRole userRole) {
		this.userName = userName; //ex. verheydenk
		this.secret = secret;
		this.name = name;
		this.firstName = firstName;
		this.address = address;
		this.postalCode = postalCode;
		this.town = town;
		this.userRole=userRole;
	}

	public void setSecret(String secret) {
			this.secret = secret;
	}

	public String getSecret() {
		return secret;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public void setUserRole(Optional<UserRole> findById) {
		// TODO Auto-generated method stub
	}

	public int getPKuser() {
		return PKuser;
	}
	
	
	
}
