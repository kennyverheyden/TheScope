package thescope.services;

import java.util.List;

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


	public List<ShopList> findAll()
	{
		return shopListRepository.findAll();
	}

	// Find products from a specific category
	// This filter is used on shop page
	public List<ShopList> findByCatFilter(String cat)
	{
		List<ShopList> products = shopListRepository.findAll();
		for(int i=0;i<products.size();i++)
		{
			if(!products.get(i).getCategory().toString().equalsIgnoreCase(cat))
			{
				products.remove(i);
			}
		}
		return products;
	}

	public ShopList findShopListById(long id) {
		return shopListRepository.findById(id).get();
	}

	public void AddShopList(ShopList shopList) {
		shopListRepository.save(shopList);
	}

}
