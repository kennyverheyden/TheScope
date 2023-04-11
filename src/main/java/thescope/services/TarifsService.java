package thescope.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import thescope.models.Tarifs;
import thescope.repositories.TarifsRepository;

@Service
@Transactional
public class TarifsService {

	@Autowired
	private TarifsRepository tarifsRepository;
	
	public TarifsService() {}
	
	
	public TarifsService(TarifsRepository tarifsRepository) {
		this.tarifsRepository = tarifsRepository;
	}
	
	public Tarifs findTarifsById(long id) {		//zoek op id
		return tarifsRepository.findById(id).get();
	}
	public List<Tarifs> findAllTarifs() {		//maak een lijst met alle tarifs
		List<Tarifs> tarifsList= tarifsRepository.findAll();
		return tarifsList;
	}
	public void addTarifs(Tarifs tarifs) {		//maak een nieuwe tarifs
		tarifsRepository.save(tarifs);
	}
	public void ChangeTarifsInactive(long id) {		//enkel actieve tarifs mogen gebruikt worden (vb bij prijsverhoging)
		tarifsRepository.findById(id).get().setActive(false);
	}
}
