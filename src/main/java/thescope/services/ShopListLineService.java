package thescope.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import thescope.exceptions.EntityNotFoundException;
import thescope.models.ShopList;
import thescope.models.ShopListLine;
import thescope.repositories.ShopListLineRepository;

import java.util.Optional;

@Service
@Transactional
public class ShopListLineService {

	@Autowired
	private ShopListLineRepository shopListLineRepository;
	
	public ShopListLineService() {}
	
	public ShopListLine findShopListLineById(long id) {
		Optional<ShopListLine> entity = shopListLineRepository.findById(id);
		return unwrapShopListLine(entity, id);
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

	public static ShopListLine unwrapShopListLine(Optional<ShopListLine> entity, Long id) {
		if (entity.isPresent()) {
			return entity.get();
		} else {
			throw new EntityNotFoundException(id, ShopListLine.class);
		}
	}
}
