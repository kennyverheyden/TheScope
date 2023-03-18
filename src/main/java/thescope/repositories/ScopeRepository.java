package thescope.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import thescope.models.Movie;
import thescope.repositories.mappers.MovieRowMapper;

import java.util.List;

@Repository
public class ScopeRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public ScopeRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Movie findMoviebyTitle(String title) {
        String sqlFindMovie = "SELECT * FROM movies WHERE title = ?";
        return (Movie) jdbc.query(sqlFindMovie, new MovieRowMapper(), title);
    }

    public List<Movie> findAllMovies() {
        String sqlFindAllMovies = "SELECT * FROM movies";
        return jdbc.query(sqlFindAllMovies, new MovieRowMapper());
    }
}
