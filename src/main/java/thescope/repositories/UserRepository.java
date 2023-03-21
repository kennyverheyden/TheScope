package thescope.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import thescope.models.User;

@Repository
public class UserRepository {

	
	//*************************************************
	
	// Repository also injected in class LoginProcessor
	
	//************************************************
	
	
	//Database connection
	@Autowired
	private JdbcTemplate jdbc;

	public List<User> selectUsers() {
		String sqlSelect = "SELECT eMailuserID, FKuserRole, secret, name, firstname, address, postalCode, town FROM tblUsers";

		RowMapper<User> rowMapper = (r, i) -> {
			User rowObject =
					new User(r.getString("eMailuserID"),r.getString("secret"),r.getString("name"),r.getString("firstname"),r.getString("address"),r.getString("postalCode"),r.getString("town"),r.getInt("FKuserRole"));
			return rowObject;
		};
		return jdbc.query(sqlSelect, rowMapper);
	}
	
	  public void storeUsers(User user) {
	        String sqlStore = "INSERT INTO tblUsers (eMailuserID,FKuserRole,secret,name,firstname,address,postalCode,town) VALUES (?,?,?,?,?,?,?,?)";
	        jdbc.update(sqlStore, user.getUserID(),user.getRole(),user.getSecret(),user.getName(),user.getFirstName(),user.getAddress(),user.getPostalCode(),user.getTown());
	    }

}