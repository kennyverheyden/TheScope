package thescope.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thescope.models.ShopList;

@Repository
public interface ShopListRepository extends JpaRepository<ShopList,Long>{
	

}
