package thescope.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import thescope.exceptions.EntityNotFoundException;
import thescope.models.ShopList;
import thescope.repositories.ShopListRepository;

@Service
@Transactional
public class ShopListService {

	@Autowired
	private ShopListRepository shopListRepository;

	public ShopListService() {}


	public List<ShopList> findAll()
	{
		return shopListRepository.findAll();
	}

	// Find products from a specific category
	// This filter is used on shop page
	public List<ShopList> findByCatFilter(String cat)
	{
		List<ShopList> products = shopListRepository.findAll();
		List<ShopList> filteredProducts = new ArrayList();
		for(ShopList product:products)
		{
			if(product.getCategory().toString().equalsIgnoreCase(cat))
			{
				filteredProducts.add(product);
			}
		}
		return filteredProducts;
	}

	public ShopList findShopListById(long id) {
		Optional<ShopList> entity = shopListRepository.findById(id);
		return unwrapShopList(entity, id);
	}

	public void AddShopList(ShopList shopList) {
		shopListRepository.save(shopList);
	}
	public void removeShopList(ShopList shopList) {
		shopListRepository.delete(shopList);
	}

	public static ShopList unwrapShopList(Optional<ShopList> entity, Long id) {
		if (entity.isPresent()) {
			return entity.get();
		} else {
			throw new EntityNotFoundException(id, ShopList.class);
		}
	}
}
