package thescope.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import thescope.models.TarifsList;
import thescope.repositories.TarifsListRepository;


@Service
@Transactional
public class TarifsListService {

	private final TarifsListRepository tarifsListRepository;
	
	@Autowired
	public TarifsListService(TarifsListRepository tarifsListRepository) {
		this.tarifsListRepository=tarifsListRepository;
	}
	public TarifsList findTarifslistById(long id) {
		return tarifsListRepository.findById(id).get();
	}
	public void addTarifsList(TarifsList tarifsList) {
		tarifsListRepository.save(tarifsList);
	}
}
