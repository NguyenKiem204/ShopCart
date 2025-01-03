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
public class SellerDetail {
    private String productID;
    private String sellerID;
    private String name;
    private String categoryID;
    private String productImageURL;
    private BigDecimal originalPrice;
    private BigDecimal currentPrice;
    private String phoneNumber;
    private String address;
    private String sellerImgURL;
    private String firstName;
    private String lastName;

    public SellerDetail() {
    }

    public SellerDetail(String productID, String sellerID, String name, String categoryID, String productImageURL, BigDecimal originalPrice, BigDecimal currentPrice, String phoneNumber, String address, String sellerImgURL, String firstName, String lastName) {
        this.productID = productID;
        this.sellerID = sellerID;
        this.name = name;
        this.categoryID = categoryID;
        this.productImageURL = productImageURL;
        this.originalPrice = originalPrice;
        this.currentPrice = currentPrice;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.sellerImgURL = sellerImgURL;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getProductImageURL() {
        return productImageURL;
    }

    public void setProductImageURL(String productImageURL) {
        this.productImageURL = productImageURL;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSellerImgURL() {
        return sellerImgURL;
    }

    public void setSellerImgURL(String sellerImgURL) {
        this.sellerImgURL = sellerImgURL;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "SellerDetail{" + "productID=" + productID + ", sellerID=" + sellerID + ", name=" + name + ", categoryID=" + categoryID + ", productImageURL=" + productImageURL + ", originalPrice=" + originalPrice + ", currentPrice=" + currentPrice + ", phoneNumber=" + phoneNumber + ", address=" + address + ", sellerImgURL=" + sellerImgURL + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }
    
}
