package thescope.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="tblUserRole")
public class UserRole{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long PKuserRole;

	@Column(name="roleName")
	private String roleName;

	public UserRole()
	{
	}

	public UserRole(String roleName)
	{
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
		return roleName.toString();
	}

}
