package thescope.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="tblShopList")
public class ShopList {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long PKshop;
	
	@Column(name="artNO")
    long articleId;
	
	@Column(name="description")
	String description;
	
	@Column(name="category")
	ShopCategory category;
	
	@Column(name="inStock")
    int inStock; // How many left
	
	@Column(name="orderQuantity")
    int orderQuantity;
	
	@Column(name="priceTAXexcl")
    BigDecimal priceTaxEx;
	
	@Column(name="priceTAXincl")
    BigDecimal priceTaxIn;


    /**construct**/
    public ShopList(long articleId, String description, ShopCategory category, int inStock, int orderQuantity, BigDecimal priceTaxEx, BigDecimal priceTaxIn) {
        this.articleId = articleId;
        this.description = description;
        this.inStock = inStock;
        this.orderQuantity = orderQuantity;
        this.priceTaxEx = priceTaxEx;
        this.priceTaxIn = priceTaxIn;
        this.category=category;
    }

    /**get&set**/
    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public BigDecimal getPriceTaxIn() {
        return priceTaxIn;
    }

    public void setPriceTaxIn(BigDecimal priceTaxIn) {
        this.priceTaxIn = priceTaxIn;
    }

    public BigDecimal getPriceTaxEx() {
        return priceTaxEx;
    }

    public void setPriceTaxEx(BigDecimal priceTaxEx) {
        this.priceTaxEx = priceTaxEx;
    }
}
