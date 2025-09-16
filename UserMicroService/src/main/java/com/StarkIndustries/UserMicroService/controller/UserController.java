package com.StarkIndustries.UserMicroService.controller;

import com.StarkIndustries.UserMicroService.keys.Keys;
import com.StarkIndustries.UserMicroService.model.User;
import com.StarkIndustries.UserMicroService.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
=======
>>>>>>> 56c7ead147886cae4b67f5426d1e0fda622b3fb8
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

<<<<<<< HEAD
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
=======
>>>>>>> 56c7ead147886cae4b67f5426d1e0fda622b3fb8
    @Autowired
    public UserService userService;

    @GetMapping("/greetings")
    public ResponseEntity<?> greetings(){
        return ResponseEntity.ok("Greetings,I am Optimus Prime!!");
    }

<<<<<<< HEAD
    public int count=0;

=======
>>>>>>> 56c7ead147886cae4b67f5426d1e0fda622b3fb8
    @PostMapping("/add-user")
    public ResponseEntity<?> addUser(@RequestBody User user){

        User user1 = this.userService.addUse(user);
        if(user1!=null)
            return ResponseEntity.status(HttpStatus.OK).body(user1);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(user1);
    }

    @GetMapping("/get-users")
<<<<<<< HEAD
//    @CircuitBreaker(name = "getAllUsersBreaker",fallbackMethod = "getAllUsersFallBack")
    @Retry(name = "getAllUsersRetry",fallbackMethod = "getAllUsersFallBack")
    public ResponseEntity<?> getUsers(){
        log.info("count:{}",count++);
=======
    @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "hotelAndRatingBreakerFallback")
    @Retry(name = "ratingHotelRetry",fallbackMethod = "")
    public ResponseEntity<?> getUsers(){
>>>>>>> 56c7ead147886cae4b67f5426d1e0fda622b3fb8
        List<User> userList = this.userService.getUsers();
        if(userList.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enter users first!!");
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

<<<<<<< HEAD
    public ResponseEntity<?> getAllUsersFallBack(Exception exception){

        Map<String, Object> response = new HashMap<>();

        log.info("Message:Service down!!,{}",exception.getLocalizedMessage());
        exception.printStackTrace();

        response.put(Keys.TIME_STAMP, Instant.now());
        response.put(Keys.STATUS,HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put(Keys.MESSAGE,"Service down!!");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

    }

    @GetMapping("/get-user-by-id/{userId}")
//    @CircuitBreaker(name = "getUserByIdBreaker",fallbackMethod = "getUserByIdFallback")
    @Retry(name = "getUserByIdRetry",fallbackMethod = "getUserByIdFallback")
    public ResponseEntity<?> getUserById(@PathVariable("userId") String userId){

        log.info("count:{}",count++);

=======
    @GetMapping("/get-user-by-id/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "hotelAndRatingFallback")
    @Retry(name = "ratingHotelRetry",fallbackMethod = "")
    public ResponseEntity<?> getUserById(@PathVariable("userId") String userId){

>>>>>>> 56c7ead147886cae4b67f5426d1e0fda622b3fb8
        User user = this.userService.getUser(userId);
        if(user!=null)
            return ResponseEntity.status(HttpStatus.OK).body(user);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!!");

    }

<<<<<<< HEAD

    public ResponseEntity<?> getUserByIdFallback(String userId,Exception exception){
=======
    public ResponseEntity<?> hotelAndRatingBreakerFallback(Exception exception){

>>>>>>> 56c7ead147886cae4b67f5426d1e0fda622b3fb8
        Map<String, Object> response = new HashMap<>();

        log.info("Message:Service down!!,{}",exception.getLocalizedMessage());
        exception.printStackTrace();

        response.put(Keys.TIME_STAMP, Instant.now());
        response.put(Keys.STATUS,HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put(Keys.MESSAGE,"Service down!!");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
<<<<<<< HEAD
    }

=======

    }


>>>>>>> 56c7ead147886cae4b67f5426d1e0fda622b3fb8
    @PutMapping("/update-user/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") String userId,@RequestBody User user){

        User user1 = this.userService.updateUser(user,userId);
        if(user1!=null)
            return ResponseEntity.status(HttpStatus.OK).body(user1);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!!");
    }

    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") String userId){
        if(this.userService.deleteUser(userId))
            return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully!!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!!");
    }
}
