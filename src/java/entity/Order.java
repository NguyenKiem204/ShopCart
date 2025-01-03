/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author nkiem
 */
import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String orderID;
    private String userID;
    private Date orderDate;
    private String status;
    private String shippingAddress;
    private BigDecimal totalAmount;
    private String paymentMethod;

    public Order(String userID, Date orderDate, String status, String shippingAddress, BigDecimal totalAmount, String paymentMethod) {
        this.userID = userID;
        this.orderDate = orderDate;
        this.status = status;
        this.shippingAddress = shippingAddress;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
    }

    public Order(String orderID, String userID, Date orderDate, String status, String shippingAddress, BigDecimal totalAmount, String paymentMethod) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderDate = orderDate;
        this.status = status;
        this.shippingAddress = shippingAddress;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
    }

    public Order() {
    }

    // Getters and Setters
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
