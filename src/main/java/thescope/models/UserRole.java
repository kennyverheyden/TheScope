package thescope.models;

public class UserRole {

	private String roleName;

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
