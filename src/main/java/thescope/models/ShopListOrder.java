package thescope.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="tblshopListOrder")
public class ShopListOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long shopListOrderId;
	
	@ManyToOne
	@JoinColumn(name= "FKBookingId")
	private Booking booking;
	
	@ManyToOne
	@JoinColumn(name= "FKShopListLineId")
	private ShopListLine shopListLine;
	
	
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	public ShopListLine getShopListLine() {
		return shopListLine;
	}
	public void setShopListLine(ShopListLine shopListLine) {
		this.shopListLine = shopListLine;
	}
	public long getShopListOrderId() {
		return shopListOrderId;
	}
	
	
}
