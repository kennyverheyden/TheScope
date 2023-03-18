package thescope.repositories.mappers;

import org.springframework.jdbc.core.RowMapper;
import thescope.models.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieRowMapper  implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet r, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setTitle(r.getString("title"));
        return movie;
    }
}
