package thescope.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import thescope.models.ShopList;
import thescope.repositories.ShopListRepository;

@Service
@Transactional
public class ShopListService {
	
	@Autowired
	private ShopListRepository shopListRepository;
	
	public ShopListService() {}
	public ShopListService(ShopListRepository shopListRepository) {
		this.shopListRepository=shopListRepository;
	}
	
	public ShopList findShopListById(long id) {
		return shopListRepository.findById(id).get();
	}
	
	public void AddShopList(ShopList shopList) {
		shopListRepository.save(shopList);
	}

}
