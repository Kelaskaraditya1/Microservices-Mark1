package com.StarkIndustries.RatingsMicroService.controller;

import com.StarkIndustries.RatingsMicroService.model.Ratings;
import com.StarkIndustries.RatingsMicroService.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingsController {

    @Autowired
    public RatingService ratingService;

    @PostMapping("/add-rating")
    public ResponseEntity<?> addRating(@RequestBody Ratings ratings){
        Ratings ratings1 = this.ratingService.addRating(ratings);
        if(ratings1!=null)
            return ResponseEntity.status(HttpStatus.OK).body(ratings1);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add Rating!!");
    }

    @GetMapping("/get-rating/{ratingId}")
    public ResponseEntity<?> getRatingById(@PathVariable("ratingId") String ratingId){
        Ratings ratings = this.ratingService.getRating(ratingId);
        if(ratings!=null)
            return ResponseEntity.status(HttpStatus.OK).body(ratings);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rating doesn't exist!!");
    }

    @GetMapping("/get-ratings")
    public ResponseEntity<?> getRatings(){
        List<Ratings> ratingsList = this.ratingService.getRatings();
        if(!ratingsList.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(ratingsList);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enter ratings first!!");
    }

    @PutMapping("/update-rating/{ratingId}")
    public ResponseEntity<?> updateRating(@PathVariable("ratingId") String ratingId,@RequestBody Ratings ratings){
        Ratings ratings1 = this.ratingService.updateRatings(ratingId,ratings);
        if(ratings1!=null)
            return ResponseEntity.status(HttpStatus.OK).body(ratings1);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rating doesn't exist!!");

    }

    @DeleteMapping("/delete-rating/{ratingId}")
    public ResponseEntity<?> deleteRating(@PathVariable("ratingId") String ratingId){

        if(this.ratingService.deleteRating(ratingId))
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully!!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rating doesn't exist!!");
    }

    @GetMapping("/get-ratings-userId/{userId}")
    public ResponseEntity<?> findByUserId(@PathVariable("userId") String userId){
        List<Ratings> ratingsList = this.ratingService.findByUserId(userId);
        if(!ratingsList.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(ratingsList);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enter ratings first!!");
    }

    @GetMapping("/get-ratings-hotelId/{hotelId}")
    public ResponseEntity<?> findByHotelId(@PathVariable("hotelId") String hotelId){
        List<Ratings> ratingsList = this.ratingService.findByHotelId(hotelId);
        if(!ratingsList.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(ratingsList);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enter ratings first!!");
    }





}
