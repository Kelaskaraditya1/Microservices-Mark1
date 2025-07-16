package com.StarkIndustries.UserMicroService.repository;

import com.StarkIndustries.UserMicroService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
}
