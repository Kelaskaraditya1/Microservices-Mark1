package com.StarkIndustries.HotelMicroService.HotelMicroservice.service;

import com.StarkIndustries.HotelMicroService.HotelMicroservice.model.Hotel;
import com.StarkIndustries.HotelMicroService.HotelMicroservice.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelService {

    @Autowired
    public HotelRepository hotelRepository;

    @CachePut(key = "#hotel.hotelId",value = "hotel")
    @CacheEvict(value = "allHotels",allEntries = true)
    public Hotel addHotel(Hotel hotel){

        String hotelId = UUID.randomUUID().toString();
        if(!this.hotelRepository.existsById(hotelId)){
            hotel.setHotelId(hotelId);
            return this.hotelRepository.save(hotel);
        }
        return null;
    }

    @Cacheable(key = "#hotelId" ,value="hotel")
    public Hotel getHotelById(String hotelId){
        if(this.hotelRepository.existsById(hotelId))
            return this.hotelRepository.findById(hotelId).get();
        return null;
    }

    @Cacheable(value = "allHotels")
    public List<Hotel> getAllHotels(){
        return this.hotelRepository.findAll();
    }

    @CachePut(key = "#hotelId",value = "hotel")
    @CacheEvict(value = "allHotels",allEntries = true)
    public Hotel updateHotel(Hotel hotel,String hotelId){
        if(this.hotelRepository.existsById(hotelId)){
            Hotel hotel1 = this.hotelRepository.findById(hotelId).get();
            hotel1.setName(hotel.getName());
            hotel1.setLocation(hotel.getLocation());
            hotel1.setContactNo(hotel.getContactNo());
            this.hotelRepository.save(hotel1);
            return hotel1;
        }
        return null;
    }

    @CacheEvict(value = {"hotel","allHotels"},key = "#hotelId",allEntries = true)
    public boolean deleteHotel(String hotelId){
        if(this.hotelRepository.existsById(hotelId)){
            this.hotelRepository.deleteById(hotelId);
            return true;
        }
        return false;
    }
}
