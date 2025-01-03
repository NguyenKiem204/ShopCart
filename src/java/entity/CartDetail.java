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
public class CartDetail {
    private String cartItemID;
    private String cartID;
    private String userID;
    private String productID;
    private int quantity;
    private String imageURL;
    private String sellerID;
    private String sellerName;
    private String productName;
    private BigDecimal originalPrice;
    private BigDecimal currentPrice;

    public CartDetail() {
    }

    public CartDetail(String cartItemID, String cartID, String userID, String productID, int quantity, String imageURL, String sellerID, String sellerName, String productName, BigDecimal originalPrice, BigDecimal currentPrice) {
        this.cartItemID = cartItemID;
        this.cartID = cartID;
        this.userID = userID;
        this.productID = productID;
        this.quantity = quantity;
        this.imageURL = imageURL;
        this.sellerID = sellerID;
        this.sellerName = sellerName;
        this.productName = productName;
        this.originalPrice = originalPrice;
        this.currentPrice = currentPrice;
    }

    public String getCartItemID() {
        return cartItemID;
    }

    public void setCartItemID(String cartItemID) {
        this.cartItemID = cartItemID;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return "CartDetail{" + "cartItemID=" + cartItemID + ", cartID=" + cartID + ", userID=" + userID + ", productID=" + productID + ", quantity=" + quantity + ", imageURL=" + imageURL + ", sellerID=" + sellerID + ", sellerName=" + sellerName + ", productName=" + productName + ", originalPrice=" + originalPrice + ", currentPrice=" + currentPrice + '}';
    }

        
}
