package com.StarkIndustries.UserMicroService.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import com.StarkIndustries.UserMicroService.model.Ratings;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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

}
