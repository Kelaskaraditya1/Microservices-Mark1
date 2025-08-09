package com.StarkIndustries.UserMicroService.service;

import com.StarkIndustries.UserMicroService.feign.service.HotelFeignService;
import com.StarkIndustries.UserMicroService.feign.service.RatingFeignService;
import com.StarkIndustries.UserMicroService.keys.Keys;
import com.StarkIndustries.UserMicroService.model.Hotel;
import com.StarkIndustries.UserMicroService.model.Ratings;
import com.StarkIndustries.UserMicroService.model.User;
import com.StarkIndustries.UserMicroService.repository.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    public HotelFeignService hotelFeignService;

    @Autowired
    public RatingFeignService ratingFeignService;

    @CachePut(key = "#user.userId",value = "user")
    @CacheEvict(value = "allUsers",allEntries = true)
    public User addUse(User user){

        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        if(!this.userRepository.existsById(userId))
            return this.userRepository.save(user);
        return null;

    }

//    @Cacheable(key = "#userId", value = "user")
    public User getUser(String userId) {

        if(this.userRepository.existsById(userId)){


            User user = this.userRepository.findById(userId).get();

            List<Ratings> ratingsList = new ArrayList<>();

            // Using RestTemplate: Api-Call

//            ResponseEntity<List<Ratings>> ratingsListResponse = this.restTemplate
//                    .exchange(
//                            Keys.USER_RATINGS_URL,
//                            HttpMethod.GET,
//                            null,
//                            new ParameterizedTypeReference<List<Ratings>>() {},
//                            userId
//                    );
//
//            if(ratingsListResponse.getBody()!=null && !ratingsListResponse.getBody().isEmpty())
//                    ratingsList=ratingsListResponse.getBody();
//
//            ratingsList.stream()
//                            .forEach(ratings -> {
//
//                                String hotelId = ratings.getHotelId();
//
//                                ResponseEntity<Hotel> hotelResponseEntity = this.restTemplate.exchange(
//                                        Keys.HOTEL_URL,
//                                        HttpMethod.GET,
//                                        null,
//                                        new ParameterizedTypeReference<Hotel>() {}
//                                        ,hotelId
//                                );
//
//                                if(hotelResponseEntity.getBody()!=null)
//                                        ratings.setHotel(hotelResponseEntity.getBody());
//                            });



            // Using Feign client

            ratingsList = this.ratingFeignService.getRatings(userId);

            ratingsList.stream().forEach(ratings -> {

                var hotelResponse = this.hotelFeignService.getHotel(ratings.getHotelId());
                if(hotelResponse!=null)
                    ratings.setHotel(hotelResponse);

                    }
            );

            user.setRatingsList(ratingsList);

            return user;
        }
        return null;

    }


//    @Cacheable(value = "allUsers")
    public List<User> getUsers(){

        List<User> userList = this.userRepository.findAll();

        // Using Rest Template-Api call.

//        userList.stream().forEach(user->{
//
//            // Api call for getting list of ratings, given by the user
//
//            var ratingsResponse = this.restTemplate.exchange(
//                    Keys.USER_RATINGS_URL,
//                    HttpMethod.GET,
//                    null,
//                    new ParameterizedTypeReference<List<Ratings>>() {}
//                    ,user.getUserId()
//            );
//
//            List<Ratings> ratingsList=null;
//
//            if(ratingsResponse.getBody()!=null){
//                ratingsList = new ArrayList<>(ratingsResponse.getBody());
//                user.setRatingsList(ratingsList);
//            }
//
//            if(ratingsList!=null){
//
//
//                ratingsList.stream().forEach(ratings -> {
//
//                    // Api call for getting Hotel, from rating.
//
//                    var hotelResponse = this.restTemplate.exchange(
//                            Keys.HOTEL_URL,
//                            HttpMethod.GET,
//                            null,
//                            new ParameterizedTypeReference<Hotel>() {},
//                            ratings.getHotelId()
//                    );
//
//                    if(hotelResponse.getBody()!=null)
//                        ratings.setHotel(hotelResponse.getBody());
//
//                });
//            }
//        });

        // Using Feign client api call.

        userList.stream().forEach(user->{

            List<Ratings> ratingsList;

            var ratingResponse =this.ratingFeignService.getRatings(user.getUserId());

            if(!ratingResponse.isEmpty()){
            ratingsList=ratingResponse;

            user.setRatingsList(ratingsList);

            ratingsList.stream().map(ratings -> {

                var hotelResponse = this.hotelFeignService.getHotel(ratings.getHotelId());

                if(hotelResponse!=null)
                    ratings.setHotel(hotelResponse);

                return ratings;
            })
                    .toList();

            }

        });

        return userList;
    }

    @CacheEvict(value = {"allUsers"},allEntries = true)
    @CachePut(key = "#userId",value = "user")
    public User updateUser(User user,String userId){
        if(this.userRepository.existsById(userId)){
            User user1 = this.userRepository.findById(userId).get();
            user1.setName(user.getName());
            user1.setContact(user.getContact());
            user1.setEmail(user.getEmail());
            user1.setUsername(user.getUsername());
            user1.setPassword(user.getPassword());
            this.userRepository.save(user1);
            return user1;
        }
        return null;
    }

    @CacheEvict(value = {"user","allUsers"},key = "#userId",allEntries = true)
    public boolean deleteUser(String userId){
        if(this.userRepository.existsById(userId)){
            this.userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

}
