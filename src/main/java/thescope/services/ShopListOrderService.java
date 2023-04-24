package thescope.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import thescope.models.ShopListOrder;
import thescope.repositories.ShopListOrderRepository;
import thescope.repositories.ShopListRepository;

@Service
@Transactional
public class ShopListOrderService {

	@Autowired
	private ShopListOrderRepository shopListOrderRepository;
	@Autowired
	private ShopListLineService shopListLineService;
	
	public ShopListOrderService() {}
	
	public ShopListOrder findShopListOrderBuId(long id) {
		return shopListOrderRepository.findById(id).get();
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
}
