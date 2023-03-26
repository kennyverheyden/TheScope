package thescope.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import thescope.models.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long>{
	
}

