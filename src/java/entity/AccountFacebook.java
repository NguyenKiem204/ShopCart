/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author nkiem
 */
public class AccountFacebook {
    private String id;
    private String name;
    private String email;
    private Picture picture; 
    private String location;

    // Constructor
    public AccountFacebook(String id, String name, String email, Picture picture, String location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.location = location;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPictureUrl() {
        return (picture != null && picture.getData() != null) ? picture.getData().getUrl() : null;
    }

    public void setPicture(Picture picture) {
        this.picture = picture; // Thay đổi để nhận kiểu Picture
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "AccountFacebook{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pictureUrl='" + getPictureUrl() + '\'' + // Sử dụng phương thức để lấy URL
                ", location='" + location + '\'' +
                '}';
    }
}



