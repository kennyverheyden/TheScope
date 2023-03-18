package thescope.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import thescope.models.User;

@Repository
public class UserRepository {

	//Database connection
	@Autowired
	private JdbcTemplate jdbc;

	public List<User> selectUsers() {
		String sqlSelect = "SELECT userID, name, firstname, address, postalCode, town FROM tblUsers";

		RowMapper<User> rowMapper = (r, i) -> {
			User rowObject =
					new User(r.getInt("userID"),r.getString("name"),r.getString("firstname"),r.getString("address"),r.getInt("postalCode"),r.getString("town"));
			return rowObject;
		};
		return jdbc.query(sqlSelect, rowMapper);
	}

}