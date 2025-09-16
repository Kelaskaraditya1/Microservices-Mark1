package com.StarkIndustries.RatingsMicroService.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "ratings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ratings implements Serializable {

    @Id
    private String ratingId;

    private String userId;

    private String hotelId;

    private String rating;

    private String feedBack;


}
