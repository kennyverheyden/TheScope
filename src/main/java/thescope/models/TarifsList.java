package thescope.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name= "tblTarifsList")
public class TarifsList {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="FKTarifsid")
	private Tarifs tarifs;
	
	@ManyToOne
	@JoinColumn(name="FKBookingId")
	private Booking booking;
	
	@Column(name="Quantity")
	private long quantity=0;
	
	
	

	public TarifsList() {}
	
	public TarifsList(Tarifs tarifs,Booking booking, long qty) {
		this.tarifs= tarifs;
		this.quantity= qty;
		this.booking=booking;
	}

	public Tarifs getTarifs() {
		return tarifs;
	}

	public void setTarifs(Tarifs tarifs) {
		this.tarifs = tarifs;
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
