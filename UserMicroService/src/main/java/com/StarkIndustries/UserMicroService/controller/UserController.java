package com.StarkIndustries.UserMicroService.controller;

import com.StarkIndustries.UserMicroService.model.User;
import com.StarkIndustries.UserMicroService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/greetings")
    public ResponseEntity<?> greetings(){
        return ResponseEntity.ok("Greetings,I am Optimus Prime!!");
    }

    @PostMapping("/add-user")
    public ResponseEntity<?> addUser(@RequestBody User user){

        User user1 = this.userService.addUse(user);
        if(user1!=null)
            return ResponseEntity.status(HttpStatus.OK).body(user1);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(user1);
    }

    @GetMapping("/get-users")
    public ResponseEntity<?> getUsers(){
        List<User> userList = this.userService.getUsers();
        if(userList.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enter users first!!");
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @GetMapping("/get-user-by-id/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") String userId){

        User user = this.userService.getUser(userId);
        if(user!=null)
            return ResponseEntity.status(HttpStatus.OK).body(user);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!!");

    }

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
