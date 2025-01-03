/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author nkiem
 */
public class Cart {
    private String cartID;
    private String userID;

    public Cart(String userID) {
        this.userID = userID;
    }

    public Cart(String cartID, String userID) {
        this.cartID = cartID;
        this.userID = userID;
    }

    // Getters and Setters
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

    @Override
    public String toString() {
        return "Cart{" + "cartID=" + cartID + ", userID=" + userID + '}';
    }
}

