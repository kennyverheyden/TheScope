package thescope.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import thescope.models.CleaningSchedule;

@Repository
public class CleaningScheduleRepository {

	//Database connection
		@Autowired
		private JdbcTemplate jdbc;

		public List<CleaningSchedule> selectCleaningSchedule() {
			String sqlSelect = "SELECT name, firstname, movie, length, location, time, date FROM tblUsers, tblCleaningSchedule, tblScheduleShow, tblMovie, tblTheaterRoom WHERE FKuser=PKuser and FKscheduleShow=PKscheduleShow and FKmovie=PKmovie and FKtheaterRoom = PKtheaterRoom";

			RowMapper<CleaningSchedule> rowMapper = (r, i) -> {
				CleaningSchedule rowObject =
						new CleaningSchedule(r.getString("name"),r.getString("firstname"),r.getString("movie"),r.getInt("length"),r.getString("location"),r.getString("time"),r.getString("date"));
				return rowObject;
			};
			return jdbc.query(sqlSelect, rowMapper);
		}
}
