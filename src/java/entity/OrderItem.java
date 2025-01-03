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
public class OrderItem {
    private String orderItemID;
    private String orderID;
    private String productID;
    private int quantity;
    private BigDecimal price;

    public OrderItem() {
    }

    public OrderItem(String orderID, String productID, int quantity, BigDecimal price) {
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItem(String orderItemID, String orderID, String productID, int quantity, BigDecimal price) {
        this.orderItemID = orderItemID;
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
    }

    public String getOrderItemID() {
        return orderItemID;
    }

    public void setOrderItemID(String orderItemID) {
        this.orderItemID = orderItemID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItem{" + "orderItemID=" + orderItemID + ", orderID=" + orderID + ", productID=" + productID + ", quantity=" + quantity + ", price=" + price + '}';
    }

    
}

