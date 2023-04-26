package thescope.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import thescope.exceptions.EntityNotFoundException;
import thescope.models.TarifsList;
import thescope.repositories.TarifsListRepository;

import java.util.Optional;


@Service
@Transactional
public class TarifsListService {

	@Autowired
	private TarifsListRepository tarifsListRepository;
	
	public TarifsListService() {}
	
	
	public TarifsList findTarifslistById(long id) {	//zoek op id
		Optional<TarifsList> entity = tarifsListRepository.findById(id);
		return unwrapTarifsList(entity, id);
	}
	public void addTarifsList(TarifsList tarifsList) {	//maak een nieuwe tarifslist
		tarifsListRepository.save(tarifsList);
	}

	public static TarifsList unwrapTarifsList(Optional<TarifsList> entity, Long id) {
		if (entity.isPresent()) {
			return entity.get();
		} else {
			throw new EntityNotFoundException(id, TarifsList.class);
		}
	}
}
