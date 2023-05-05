package thescope.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import thescope.exceptions.EntityNotFoundException;
import thescope.models.Tarifs;
import thescope.repositories.TarifsRepository;

@Service
@Transactional
public class TarifsService {

	@Autowired
	private TarifsRepository tarifsRepository;

	public TarifsService() {
	}

	public Tarifs findTarifsById(long id) { // zoek op id
		Optional<Tarifs> entity = tarifsRepository.findById(id);
		return unwrapTarifs(entity, id);
	}

	public List<Tarifs> findAllTarifs() { // maak een lijst met alle tarifs
		return tarifsRepository.findAll();
	}

	public void addTarifs(Tarifs tarifs) { // maak een nieuwe tarifs
		tarifsRepository.save(tarifs);
	}

	public void removeTarifs(Tarifs tarifs) { // verwijder een tarifs
		tarifsRepository.delete(tarifs);
	}

	public void ChangeTarifsInactive(long id) { // enkel actieve tarifs mogen gebruikt worden (vb bij prijsverhoging)
		Optional<Tarifs> entity = tarifsRepository.findById(id);
		unwrapTarifs(entity, id).setActive(false);
	}

	public static Tarifs unwrapTarifs(Optional<Tarifs> entity, Long id) {
		if(entity.isPresent()) {
			return entity.get();
		} else {
			throw new EntityNotFoundException(id, Tarifs.class);
		}
	}
}
