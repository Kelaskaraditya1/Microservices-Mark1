package com.StarkIndustries.UserMicroService.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ratings {

    private String ratingId;

    private String userId;

    private String hotelId;

    private String rating;

    private String feedBack;

    private Hotel hotel;


}
