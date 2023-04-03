package thescope.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class TarifsList {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
}
