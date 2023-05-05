package thescope.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import thescope.exceptions.EntityNotFoundException;
import thescope.models.ShopListOrder;
import thescope.repositories.ShopListOrderRepository;
import thescope.repositories.ShopListRepository;

import java.util.Optional;

@Service
@Transactional
public class ShopListOrderService {

	@Autowired
	private ShopListOrderRepository shopListOrderRepository;
	@Autowired
	private ShopListLineService shopListLineService;
	
	public ShopListOrderService() {}
	
	public ShopListOrder findShopListOrderBuId(long id) {
		Optional<ShopListOrder> entity = shopListOrderRepository.findById(id);
		return unwrapShopListOrder(entity, id);
	}
	public void addShopListOrder(ShopListOrder shopListOrder) {
		shopListOrderRepository.save(shopListOrder);
	}
	public void deleteShopListOrder(ShopListOrder shopListOrder) {
		shopListOrderRepository.delete(shopListOrder);
	}
	public double calculateOrder(ShopListOrder shopListOrder) {
		return shopListLineService.calculateLine(shopListOrder.getShopListLine());
	}

	public static ShopListOrder unwrapShopListOrder(Optional<ShopListOrder> entity, Long id) {
		if (entity.isPresent()) {
			return entity.get();
		} else {
			throw new EntityNotFoundException(id, ShopListOrder.class);
		}
	}
}
