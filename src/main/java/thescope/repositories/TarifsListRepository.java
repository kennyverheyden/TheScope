package thescope.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import thescope.models.TarifsList;

@Repository
public interface TarifsListRepository extends JpaRepository<TarifsList, Long>{

}
