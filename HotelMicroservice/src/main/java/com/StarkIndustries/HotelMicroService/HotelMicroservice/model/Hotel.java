package com.StarkIndustries.HotelMicroService.HotelMicroservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Entity(name = "hotel")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Hotel implements Serializable {

    @Id
    private String hotelId;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "phone_no")
    private String contactNo;
}
