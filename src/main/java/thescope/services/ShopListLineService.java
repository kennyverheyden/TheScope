package thescope.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import thescope.models.ShopList;
import thescope.models.ShopListLine;
import thescope.repositories.ShopListLineRepository;

@Service
@Transactional
public class ShopListLineService {

	@Autowired
	private ShopListLineRepository shopListLineRepository;
	
	public ShopListLineService() {}
	
	
	
	public ShopListLine findShopListLineById(long id) {
		return shopListLineRepository.findById(id).get();
	}
	public void addShopListLine(ShopListLine shopListLine) {
		shopListLineRepository.save(shopListLine);
	}
	public void deleteShopListLine(ShopListLine shopListLine) {
		shopListLineRepository.delete(shopListLine);
	}
	public double calculateLine(ShopListLine shopListLine) {
		return shopListLine.getShopList().getPriceTaxIn()*shopListLine.getQuantity();
	}
}
