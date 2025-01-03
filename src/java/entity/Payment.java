/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author nkiem
 */
import java.util.Date;

public class Payment {

    private int paymentID;
    private int orderID;
    private Date paymentDate;
    private double paymentAmount;
    private String paymentMethod;

    public Payment(int orderID, Date paymentDate, double paymentAmount, String paymentMethod) {
        this.orderID = orderID;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
    }

    public Payment() {
    }

    // Getters and Setters
    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
