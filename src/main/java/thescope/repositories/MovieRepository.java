package thescope.repositories;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thescope.models.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long>{



}