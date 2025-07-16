package com.StarkIndustries.RatingsMicroService.repository;

import com.StarkIndustries.RatingsMicroService.model.Ratings;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Ratings,String> {

    public List<Ratings> findByUserId(String userId);

    public List<Ratings> findByHotelId(String hotelId);
}
