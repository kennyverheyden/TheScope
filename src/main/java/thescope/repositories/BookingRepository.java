package thescope.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import thescope.models.Booking;

@Repository
public class BookingRepository {

	//Database connection
	@Autowired
	private JdbcTemplate jdbc;

	public List<Booking> selectBookings() {
		String sqlSelect = "SELECT name, firstname, movie, time, date, location FROM tblBookings, tblUsers, tblScheduleShow, tblMovie, tblTheaterRoom where FKuser=PKuser and FKscheduleShow=PKscheduleShow and PKscheduleShow = PKmovie and PKtheaterRoom = FKtheaterRoom";

		RowMapper<Booking> rowMapper = (r, i) -> {
			Booking rowObject =
					new Booking(r.getString("name"),r.getString("firstname"),r.getString("movie"),r.getString("time"),r.getString("date"),r.getString("location"));
			return rowObject;
		};
		return jdbc.query(sqlSelect, rowMapper);
	}

}
