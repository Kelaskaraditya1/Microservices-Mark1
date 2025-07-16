package com.StarkIndustries.UserMicroService.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class RatingsDto {
    private String ratingId;
    private String userId;
    private String hotelId;
    private String rating;
    private String feedBack;
}
