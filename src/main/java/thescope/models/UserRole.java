package thescope.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="tblUserRole")
public class UserRole{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long PKrole;

	@Column(name="roleName")
	String roleName;

	public UserRole()
	{
	}

	public UserRole(String roleName) {
		this.roleName=roleName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "UserRole [roleName=" + roleName + "]";
	}

}
