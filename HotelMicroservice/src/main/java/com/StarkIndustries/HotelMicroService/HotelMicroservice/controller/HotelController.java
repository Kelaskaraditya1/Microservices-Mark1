package com.StarkIndustries.HotelMicroService.HotelMicroservice.controller;

import com.StarkIndustries.HotelMicroService.HotelMicroservice.model.Hotel;
import com.StarkIndustries.HotelMicroService.HotelMicroservice.service.HotelService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    public HotelService hotelService;

    @GetMapping("/greetings")
    public ResponseEntity<?> greetings(){
        return ResponseEntity.status(HttpStatus.OK).body("Greetings,I am Optimus prime");
    }

    @PostMapping("/add-hotel")
    public ResponseEntity<?> addHotel(@RequestBody Hotel hotel){
        Hotel hotel1 = this.hotelService.addHotel(hotel);
        if(hotel1!=null)
            return ResponseEntity.status(HttpStatus.OK).body(hotel1);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add Hotel!!");
    }

    @GetMapping("/get-hotel/{hotelId}")
    public ResponseEntity<?> getUser(@PathVariable("hotelId") String hotelId){

        Hotel hotel = this.hotelService.getHotelById(hotelId);
        if(hotel!=null)
            return ResponseEntity.status(HttpStatus.OK).body(hotel);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel not found!!");

    }

    @GetMapping("/get-hotels")
    public ResponseEntity<?> getAllHotels(){
        List<Hotel> hotelList = this.hotelService.getAllHotels();
        if(!hotelList.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(hotelList);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enter hotels first!!");
    }

    @PutMapping("/update-hotel/{hotelId}")
    public ResponseEntity<?> updateHotel(@PathVariable("hotelId") String hotelId,@RequestBody Hotel hotel){

        Hotel hotel1 = this.hotelService.updateHotel(hotel,hotelId);
        if(hotel1!=null)
            return ResponseEntity.status(HttpStatus.OK).body(hotel1);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel not found!!");
    }

    @DeleteMapping("/delete-hotel/{hotelId}")
    public ResponseEntity<?> deleteHotel(@PathVariable("hotelId") String hotelId){
        if(this.hotelService.deleteHotel(hotelId))
            return ResponseEntity.status(HttpStatus.OK).body("Hotel Deleted successfully!!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel not found!!");
    }}
