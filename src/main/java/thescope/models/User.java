package thescope.models;

public class User {
    private int userId;
    private String name;
    private String firstName;
    private String address;
    private int postalCode;
    private String town;


    /**contructor**/

    public User() {

    }
    public User(int userId, String name, String firstName, String address, int postalCode, String town) {
        this.userId = userId;
        this.name = name;
        this.firstName = firstName;
        this.address = address;
        this.postalCode = postalCode;
        this.town = town;
    }


    /**get&set**/

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
