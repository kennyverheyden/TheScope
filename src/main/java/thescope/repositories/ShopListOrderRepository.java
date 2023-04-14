package thescope.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import thescope.models.ShopList;
import thescope.models.ShopListOrder;

@Repository
public interface ShopListOrderRepository extends JpaRepository<ShopListOrder,Long>{

}
