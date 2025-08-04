package com.StarkIndustries.RatingsMicroService.repository;

import com.StarkIndustries.RatingsMicroService.model.Ratings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RatingsRepositoryUsingCriteria {

    @Autowired
    public MongoTemplate mongoTemplate;

    @Autowired
    public RatingRepository ratingRepository;

    public List<Ratings> findByUserId(String userId){
        List<Ratings> allRatings = this.ratingRepository.findAll();

        Query query = new Query();

        Criteria criteria1 = Criteria.where("userId").exists(true);
        Criteria criteria2 = Criteria.where("userId").is(userId);

        query.addCriteria(criteria1.andOperator(criteria2));

        List<Ratings> ratingsList = this.mongoTemplate.find(query,Ratings.class);
        return ratingsList;
    }

    public List<Ratings> findByHotelId(String hotelId){

        List<Ratings> ratingsList = this.ratingRepository.findAll();

        Query query = new Query();

        Criteria criteria = Criteria.where("hotelId").exists(true).and("hotelId").is(hotelId);

        query.addCriteria(criteria);

        List<Ratings> ratings = this.mongoTemplate.find(query,Ratings.class);

        return ratings;

    }
}
