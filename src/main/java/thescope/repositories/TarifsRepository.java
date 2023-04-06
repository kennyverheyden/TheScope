package thescope.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import thescope.models.Tarifs;
import thescope.services.TarifsService;

@Repository
public interface TarifsRepository  extends JpaRepository<Tarifs,Long> {

	

}
