package thescope.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="tblshopListline")
public class ShopListLine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long shopListLineId;
	
	@ManyToOne
	@JoinColumn(name= "FKshopListID")
	private ShopList shopList;
	
	private long quantity;
	
	public ShopList getShopList() {
		return shopList;
	}
	public void setShopList(ShopList shopList) {
		this.shopList = shopList;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public long getShopListLineId() {
		return shopListLineId;
	}
	
	
}
