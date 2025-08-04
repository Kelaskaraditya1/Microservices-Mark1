package com.StarkIndustries.HotelMicroService.HotelMicroservice.repository;

import com.StarkIndustries.HotelMicroService.HotelMicroservice.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,String> {
}
