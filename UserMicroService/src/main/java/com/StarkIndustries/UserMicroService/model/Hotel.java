package com.StarkIndustries.UserMicroService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.ws.rs.GET;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Hotel {

    private String hotelId;

    private String name;

    private String location;

    private String contactNo;
}
