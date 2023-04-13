package thescope.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import thescope.models.TarifsList;
import thescope.repositories.TarifsListRepository;


@Service
@Transactional
public class TarifsListService {

	@Autowired
	private TarifsListRepository tarifsListRepository;
	
	public TarifsListService() {}
	
	
	public TarifsList findTarifslistById(long id) {	//zoek op id
		return tarifsListRepository.findById(id).get();
	}
	public void addTarifsList(TarifsList tarifsList) {	//maak een nieuwe tarifslist
		tarifsListRepository.save(tarifsList);
	}
}
