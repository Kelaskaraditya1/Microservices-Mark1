package com.StarkIndustries.UserMicroService.service;

import com.StarkIndustries.UserMicroService.model.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTELMICROSERVICE")
public interface HotelFeignClient {

    @GetMapping("/hotel/get-hotel/{hotelId}")
    public Hotel getHotelById(@PathVariable("hotelId") String hotelId);

//    @GetMapping("/ratings/get-ratings-userId/{userId}")
//    public List<Ratings> getRatingsByUserId(@PathVariable("userId"))
}
