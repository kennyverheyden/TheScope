package thescope.models;

public enum UserRoleNames {
	SHOPSTAFF("Shop staff"),
	DESKSTAFF("Desk staff"),
	CLEANSTAFF("Clean staff"),
	ADMIN("Admin"),
	CUSTOMER("Customer");

	private final String displayText;

	UserRoleNames(String displayText) {
		this.displayText = displayText;
	}

	public String string() {
		return displayText;
	}
}

