package thescope.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class TarifsList {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private List<Tarifs> tarifsList= new ArrayList<>();
	
	private long quantity=0;
	
	public List<Tarifs> getTarifsList() {
		return tarifsList;
	}

	public void setTarifsList(List<Tarifs> tarifsList) {
		this.tarifsList = tarifsList;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	
}
