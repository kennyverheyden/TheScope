package thescope.models;

import java.math.BigDecimal;

public class ShopList {
    private long articleId;
    private String description;
    private int inStock; // How many left
    private int orderQuantity;
    private BigDecimal priceTaxEx;
    private BigDecimal priceTaxIn;


    /**construct**/
    public ShopList(long articleId, String description, int inStock, int orderQuantity, BigDecimal priceTaxEx, BigDecimal priceTaxIn) {
        this.articleId = articleId;
        this.description = description;
        this.inStock = inStock;
        this.orderQuantity = orderQuantity;
        this.priceTaxEx = priceTaxEx;
        this.priceTaxIn = priceTaxIn;
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
