package com.StarkIndustries.RatingsMicroService.service;

import com.StarkIndustries.RatingsMicroService.model.Ratings;
import com.StarkIndustries.RatingsMicroService.repository.RatingRepository;
import com.StarkIndustries.RatingsMicroService.repository.RatingsRepositoryUsingCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingService {

    @Autowired
    public RatingRepository ratingRepository;

    @Autowired
    public RatingsRepositoryUsingCriteria ratingsRepositoryUsingCriteria;

    @CacheEvict(value = "allRatings",allEntries = true)
    @CachePut(key = "#ratings.ratingId",value = "rating")
    public Ratings addRating(Ratings ratings){
        String ratingId = UUID.randomUUID().toString();
        if(!this.ratingRepository.existsById(ratingId)){
            ratings.setRatingId(ratingId);
            return this.ratingRepository.save(ratings);
        }
        return null;
    }

    @Cacheable(key = "#ratingId",value = "rating")
    public Ratings getRating(String ratingId){

        if(this.ratingRepository.existsById(ratingId)){
            Ratings ratings = this.ratingRepository.findById(ratingId).get();
            return ratings;
        }
        return null;
    }

    @Cacheable(value = "allRatings")
    public List<Ratings> getRatings(){
        return this.ratingRepository.findAll();
    }

    @CachePut(key = "#ratingId",value = "rating")
    @CacheEvict(value = "allRatings",allEntries = true)
    public Ratings updateRatings(String ratingId,Ratings ratings){
        if(this.ratingRepository.existsById(ratingId)){
            Ratings ratings1 = this.ratingRepository.findById(ratingId).get();
            ratings1.setFeedBack(ratings.getFeedBack());
            ratings1.setRating(ratings.getRating());
            ratings1.setUserId(ratings.getUserId());
            ratings1.setHotelId(ratings.getHotelId());
            this.ratingRepository.save(ratings1);
            return ratings1;
        }
        return null;
    }

    @CacheEvict(key = "ratingId",value = {"rating","allRatings"},allEntries = true)
    public boolean deleteRating(String ratingId){
        if(this.ratingRepository.existsById(ratingId)){
            this.ratingRepository.deleteById(ratingId);
            return true;
        }
        return false;
    }

    public List<Ratings> findByUserId(String userId){
        List<Ratings> ratingsList = this.ratingsRepositoryUsingCriteria.findByUserId(userId);
        return ratingsList;
    }

    public List<Ratings> findByHotelId(String hotelId){
        List<Ratings> ratingsList=this.ratingsRepositoryUsingCriteria.findByHotelId(hotelId);
        return ratingsList;
    }
}
