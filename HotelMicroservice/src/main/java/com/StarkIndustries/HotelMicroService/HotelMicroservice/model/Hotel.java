package com.StarkIndustries.HotelMicroService.HotelMicroservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity(name = "hotel")
public class Hotel implements Serializable {

    @Id
    private String hotelId;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "phone_no")
    private String contactNo;

    public Hotel(String hotelId, String name, String location, String contactNo) {
        this.hotelId = hotelId;
        this.name = name;
        this.location = location;
        this.contactNo = contactNo;
    }

    public Hotel(String name, String location, String contactNo) {
        this.name = name;
        this.location = location;
        this.contactNo = contactNo;
    }

    public Hotel() {

    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId='" + hotelId + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", contactNo='" + contactNo + '\'' +
                '}';
    }
}
