package com.StarkIndustries.UserMicroService.feign.service;

import com.StarkIndustries.UserMicroService.model.Ratings;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@FeignClient(name = "RATINGSMICROSERVICE")
public interface RatingFeignService {

    @GetMapping("/ratings/get-ratings-userId/{userId}")
    public List<Ratings> getRatings(@PathVariable("userId") String userId);

}
