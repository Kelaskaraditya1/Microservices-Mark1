package com.StarkIndustries.UserMicroService.service;

import com.StarkIndustries.UserMicroService.model.Ratings;
import jakarta.ws.rs.PathParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATINGSMICROSERVICE")
public interface RatingsFeignClient {

    @GetMapping("/ratings/get-ratings-userId/{userId}")
    public List<Ratings> getRatings(@PathVariable("userId") String userId);
}
