package thescope.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import thescope.models.UserRole;

@Repository
public class UserRoleRepository {

	//Database connection
	@Autowired
	private JdbcTemplate jdbc;

	public List<UserRole> selectUserRoles() {
		String sqlSelect = "SELECT roleName FROM tblUserRole";

		RowMapper<UserRole> rowMapper = (r, i) -> {
			UserRole rowObject =
					new UserRole(r.getString("roleName"));
			return rowObject;
		};
		return jdbc.query(sqlSelect, rowMapper);
	}

}
