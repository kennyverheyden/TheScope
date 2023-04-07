package thescope.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name= "tblTarifs")
public class Tarifs {	//Dit zijn de verschillende tarieven mogelijk te kiezen

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name= "PKtarif_id")
	private long id;
	@Column(name="FKtarifName")
	private long name;
	@Column(name="priceTaxexcl")
	private double priceTaxExcl;
	@Column(name="priceTaxincl")
	private double priceTaxIncl;
	@Column(name="3Dsurcharge")
	private double ThreeDSurcharge;
	@Column(name="Active")
	private boolean active;
	
	public Tarifs() {}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPriceTaxExcl() {
		return priceTaxExcl;
	}
	public void setPriceTaxExcl(double priceTaxExcl) {
		this.priceTaxExcl = priceTaxExcl;
	}
	public double getPriceTaxIncl() {
		return priceTaxIncl;
	}
	public void setPriceTaxIncl(double priceTaxIncl) {
		this.priceTaxIncl = priceTaxIncl;
	}
	public double getThreeDSurcharge() {
		return ThreeDSurcharge;
	}
	public void setThreeDSurcharge(double threeDSurcharge) {
		ThreeDSurcharge = threeDSurcharge;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public long getId() {
		return id;
	}
	
	
}
