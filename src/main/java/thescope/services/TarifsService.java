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

	private final TarifsRepository tarifsRepository;
	
	@Autowired
	public TarifsService(TarifsRepository tarifsRepository) {
		this.tarifsRepository = tarifsRepository;
	}
	
	public Tarifs findTarifsById(long id) {
		return tarifsRepository.findById(id).get();
	}
	public List<Tarifs> findAllTarifs() {
		List<Tarifs> tarifsList= tarifsRepository.findAll();
		return tarifsList;
	}
	public void addTarifs(Tarifs tarifs) {
		tarifsRepository.save(tarifs);
	}
	public void ChangeTarifsInactive(long id) {
		tarifsRepository.findById(id).get().setActive(false);
	}
}
