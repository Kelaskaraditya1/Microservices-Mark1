package com.StarkIndustries.UserMicroService.service;

import com.StarkIndustries.UserMicroService.keys.Keys;
import com.StarkIndustries.UserMicroService.model.Hotel;
import com.StarkIndustries.UserMicroService.model.Ratings;
import com.StarkIndustries.UserMicroService.model.User;
import com.StarkIndustries.UserMicroService.repository.UserRepository;
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

@Slf4j
@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    public HotelFeignClient hotelFeignClient;

    @Autowired
    public RatingsFeignClient ratingsFeignClient;

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

//        // Making first Api call for getting ratings.
//
//        List<Ratings> ratingsList = new ArrayList<>();
//
//        try {
//            // Construct URL with userId
//            log.info("Calling Ratings Service with URL: {}", Keys.USER_RATINGS_URL);
//
//            ResponseEntity<List<Ratings>> ratingResponse = this.restTemplate.exchange(
//                    Keys.USER_RATINGS_URL,
//                    HttpMethod.GET,
//                    null,
//                    new ParameterizedTypeReference<List<Ratings>>() {},
//                    userId
//            );
//
//            // Making 2nd Api call , for getting hotel
//
//            Hotel hotel = null;
//
//            try{
//                ResponseEntity<Hotel> hotelResponseEntity = this.restTemplate.exchange(
//                        Keys.HOTEL_URL
//                        , HttpMethod.GET
//                        , null
//                        , new ParameterizedTypeReference<Hotel>() {}
//                );
//
//                hotel = hotelResponseEntity.getBody();
//
//            }catch (Exception e){
//                e.printStackTrace();
//                log.info(e.getLocalizedMessage());
//            }
//
//            ratingsList = ratingResponse.getBody();
//            Hotel finalHotel = hotel;
//            ratingsList.stream()
//                    .map(ratings -> {
//                        ratings.setHotel(finalHotel);
//                        return finalHotel;
//                    });
//            log.info("Fetched Ratings List: {}", ratingsList);
//        } catch (Exception e) {
//            log.error("Failed to fetch ratings from Ratings Service for userId: {}", userId, e);
//        }

//<------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------>


//        try{
//
//            ResponseEntity<List<Ratings>> ratingsListResponse = this.restTemplate
//                    .exchange(
//                            Keys.USER_RATINGS_URL,
//                            HttpMethod.GET,
//                            null,
//                            new ParameterizedTypeReference<List<Ratings>>() {},
//                            userId
//                    );
//
//            ratingsList= ratingsListResponse.getBody();
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//            log.info(e.getLocalizedMessage());
//        }
//
//        if (this.userRepository.existsById(userId)) {
//            User user = this.userRepository.findById(userId).get();
//
//            try{
//
//                ResponseEntity<Hotel> hotelResponseEntity = this.restTemplate
//                        .exchange(
//                                Keys.HOTEL_URL,
//                                HttpMethod.GET,
//                                null,
//                                new ParameterizedTypeReference<Hotel>() {}
//                        );
//
//                Hotel hotel = hotelResponseEntity.getBody();
//
//                log.info("hotel object: {}",hotel);
//
//                ratingsList.stream()
//                        .forEach(ratings->
//                                ratings.setHotel(hotel));
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                log.debug(e.getLocalizedMessage());
//            }
//            user.setRatingsList(ratingsList);
//            log.info("Returning user with ratings: {}", user);
//            return user;
//        } else {
//            log.warn("User not found in database for userId: {}", userId);
//            return null;
//        }

        //<------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------>

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
//
//            user.setRatingsList(ratingsList);

            // Using Feign client

            ratingsList = this.ratingsFeignClient.getRatings(userId);

            for(Ratings rating:ratingsList){

                Hotel hotel = this.hotelFeignClient.getHotelById(rating.getHotelId());
                rating.setHotel(hotel);
            }

            user.setRatingsList(ratingsList);



            return user;
        }
        return null;

    }


    @Cacheable(value = "allUsers")
    public List<User> getUsers(){

        return this.userRepository.findAll();
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
