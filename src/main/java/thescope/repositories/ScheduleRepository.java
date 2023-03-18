package thescope.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import thescope.models.ScheduleShow;

@Repository
public class ScheduleRepository {

	//Database connection
	@Autowired
	private JdbcTemplate jdbc;

	public List<ScheduleShow> selectSchedules() {
		String sqlSelect = "SELECT movie, location, time, date FROM tblMovie, tblScheduleShow, tblTheaterRoom where PKmovie=FKmovie and FKtheaterRoom=PKtheaterRoom; ";

		RowMapper<ScheduleShow> rowMapper = (r, i) -> {
			ScheduleShow rowObject =
					new ScheduleShow(r.getString("movie"),r.getString("location"),r.getString("time"),r.getString("date"));
			return rowObject;
		};
		return jdbc.query(sqlSelect, rowMapper);
	}
	
}
