package com.StarkIndustries.UserMicroService.feign.service;

import com.StarkIndustries.UserMicroService.model.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(name = "HOTELMICROSERVICE")
public interface HotelFeignService {

    @GetMapping("/hotel/get-hotel/{hotelId}")
    public Hotel getHotel(@PathVariable("hotelId") String hotelId);


}
