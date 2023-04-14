package thescope.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="tblShopList")
public class ShopList {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long PKshop;

	//	@Column(name="artNO")
	//    long articleId;

	@Column(name="description")
	String description;

	@Enumerated(EnumType.STRING)
	@Column(name="category")
	ShopCategory category;

	@Column(name="stock")
	int inStock; // How many left

	@Column(name="orderQTY")
	int orderQuantity;

	@Column(name="priceTAXexcl")
	Double priceTaxEx;

	@Column(name="priceTAXincl")
	Double priceTaxIn;


	/**construct**/
	public ShopList() {}

	public ShopList(String description, ShopCategory category, int inStock, int orderQuantity, Double priceTaxEx, Double priceTaxIn) {

		this.description = description;
		this.inStock = inStock;
		this.orderQuantity = orderQuantity;
		this.priceTaxEx = priceTaxEx;
		this.priceTaxIn = priceTaxIn;
		this.category=category;
	}

	/**get&set**/
	public long getArticleId() {
		return PKshop;
	}
	
	//
	//    public void setArticleId(long articleId) {
	//        this.articleId = articleId;
	//    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ShopCategory getCategory() {
		return category;
	}

	public void setCategory(ShopCategory category) {
		this.category = category;
	}

	public int isInStock() {
		return inStock;
	}

	public void setInStock(int inStock) {
		this.inStock = inStock;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Double getPriceTaxIn() {
		return priceTaxIn;
	}

	public void setPriceTaxIn(Double priceTaxIn) {
		this.priceTaxIn = priceTaxIn;
	}

	public Double getPriceTaxEx() {
		return priceTaxEx;
	}

	public void setPriceTaxEx(Double priceTaxEx) {
		this.priceTaxEx = priceTaxEx;
	}
}
