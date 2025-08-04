package com.StarkIndustries.UserMicroService.model;

import com.StarkIndustries.UserMicroService.dto.RatingsDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity(name = "user")
public class User implements Serializable {

    @Id
    private String userId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_no")
    private String contact;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Transient
    private List<Ratings> ratingsList;

    public User(String userId, String name, String email, String contact, String username, String password, List<Ratings> ratingsList) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.username = username;
        this.password = password;
        this.ratingsList = ratingsList;
    }

    public User(String userId, String name, String email, String contact, String username, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.username = username;
        this.password = password;
    }

    public User(String name, String email, String contact, String username, String password) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Ratings> getRatingsList() {
        return ratingsList;
    }

    public void setRatingsList(List<Ratings> ratingsList) {
        this.ratingsList = ratingsList;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
