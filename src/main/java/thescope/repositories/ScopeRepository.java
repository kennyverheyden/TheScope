package thescope.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ScopeRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public ScopeRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
}
