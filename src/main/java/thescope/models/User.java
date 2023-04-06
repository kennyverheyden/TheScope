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
	private UserRole userRole;
	
	@Column(name="eMailuserID")
	private String userName; //=email 
	
	@Column(name="secret")
	private String secret;
	
	@Column(name="name")
	private String name;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="address")
	private String address;
	
	@Column(name="postalCode")
	private String postalCode;
	
	@Column(name="town")
	private String town;

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

	public int getPKuser() {
		return PKuser;
	}

	public void setPKuser(int pKuser) {
		PKuser = pKuser;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
