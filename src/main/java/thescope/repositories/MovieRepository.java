package thescope.repositories;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import thescope.models.Movie;

@Repository
public class MovieRepository {

	//Database connection
	@Autowired
	private JdbcTemplate jdbc;

	public List<Movie> selectMovies() {
		String sqlSelect = "SELECT movie, genre, rating, movie, length, \"3D\" FROM tblmovie, tblGenres where tblmovie.FKgenre = tblGenres.PKgenre";

		RowMapper<Movie> rowMapper = (r, i) -> {
			Movie rowObject =
					new Movie(r.getString("movie"),r.getString("genre"),r.getDouble("rating"),r.getInt("length"),r.getBoolean("3D"));
			return rowObject;
		};
		return jdbc.query(sqlSelect, rowMapper);
	}

}