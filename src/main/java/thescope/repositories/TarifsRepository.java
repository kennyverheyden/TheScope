package thescope.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import thescope.models.Tarifs;

public interface TarifsRepository  extends JpaRepository<Tarifs,Long> {

}
