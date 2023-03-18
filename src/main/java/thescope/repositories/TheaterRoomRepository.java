package thescope.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import thescope.models.TheaterRoom;

@Repository
public class TheaterRoomRepository {

	//Database connection
	@Autowired
	private JdbcTemplate jdbc;

	public List<TheaterRoom> selectTheaterRooms() {
		String sqlSelect = "SELECT maxNormalSeats, maxVipSeats, location FROM tblTheaterRoom";

		RowMapper<TheaterRoom> rowMapper = (r, i) -> {
			TheaterRoom rowObject =
					new TheaterRoom(r.getInt("maxNormalSeats"),r.getInt("maxVipSeats"),r.getString("location"));
			return rowObject;
		};
		return jdbc.query(sqlSelect, rowMapper);
	}

}
