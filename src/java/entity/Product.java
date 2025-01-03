/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.math.BigDecimal;

/**
 *
 * @author nkiem
 */
public class Product {

    private String productID;
    private String sellerID;
    private String name;
    private String description;
    private String categoryID;
    private String imageURL;
    private BigDecimal originalPrice;
    private BigDecimal currentPrice;

    public Product(String productID, String sellerID, String name, String description, String categoryID, String imageURL, BigDecimal originalPrice, BigDecimal currentPrice) {
        this.productID = productID;
        this.sellerID = sellerID;
        this.name = name;
        this.description = description;
        this.categoryID = categoryID;
        this.imageURL = imageURL;
        this.originalPrice = originalPrice;
        this.currentPrice = currentPrice;
    }

    public Product(String sellerID, String name, String description, String categoryID, String imageURL, BigDecimal originalPrice, BigDecimal currentPrice) {
        this.sellerID = sellerID;
        this.name = name;
        this.description = description;
        this.categoryID = categoryID;
        this.imageURL = imageURL;
        this.originalPrice = originalPrice;
        this.currentPrice = currentPrice;
    }

    

    public Product() {
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    // Getters and Setters
    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    
}
