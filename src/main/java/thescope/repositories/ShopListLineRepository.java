package thescope.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import thescope.models.ShopList;
import thescope.models.ShopListLine;

@Repository
public interface ShopListLineRepository extends JpaRepository<ShopListLine,Long>{

}
