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
public class ProductCheckOut {
    private String productID;
    private int quantity;
    private BigDecimal totalPrice;
    private String imageURL;
    private String sellerID;
    private String sellerName;
    private String productName;
    private BigDecimal originalPrice;
    private BigDecimal currentPrice;

    public ProductCheckOut() {
    }

    @Override
    public String toString() {
        return "ProductCheckOut{" + "productID=" + productID + ", quantity=" + quantity + ", totalPrice=" + totalPrice + ", imageURL=" + imageURL + ", sellerID=" + sellerID + ", sellerName=" + sellerName + ", productName=" + productName + ", originalPrice=" + originalPrice + ", currentPrice=" + currentPrice + '}';
    }

    public ProductCheckOut(String productID, int quantity, BigDecimal totalPrice, String imageURL, String sellerID, String sellerName, String productName, BigDecimal originalPrice, BigDecimal currentPrice) {
        this.productID = productID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.imageURL = imageURL;
        this.sellerID = sellerID;
        this.sellerName = sellerName;
        this.productName = productName;
        this.originalPrice = originalPrice;
        this.currentPrice = currentPrice;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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
     public static ProductCheckOut fromString(String str) {
        String productID = extractValue(str, "productID");
        int quantity = Integer.parseInt(extractValue(str, "quantity"));
        BigDecimal totalPrice = new BigDecimal(extractValue(str, "totalPrice"));
        String imageURL = extractValue(str, "imageURL");
        String sellerID = extractValue(str, "sellerID");
        String sellerName = extractValue(str, "sellerName");
        String productName = extractValue(str, "productName");
        BigDecimal originalPrice = new BigDecimal(extractValue(str, "originalPrice"));
        BigDecimal currentPrice = new BigDecimal(extractValue(str, "currentPrice"));

        return new ProductCheckOut(productID, quantity, totalPrice, imageURL, sellerID, sellerName, productName, originalPrice, currentPrice);
    }

    private static String extractValue(String str, String key) {
        String prefix = key + "=";
        int startIndex = str.indexOf(prefix) + prefix.length();
        int endIndex = str.indexOf(",", startIndex);
        if (endIndex == -1) {
            endIndex = str.indexOf("}", startIndex); // End of object
        }
        return str.substring(startIndex, endIndex).trim();
    }
    
}
