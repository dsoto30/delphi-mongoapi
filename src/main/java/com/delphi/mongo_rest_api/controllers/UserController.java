package com.delphi.mongo_rest_api.controllers;

import com.delphi.mongo_rest_api.UserNotFoundException;
import com.delphi.mongo_rest_api.models.*;
import com.delphi.mongo_rest_api.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("delphi/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user){

        Optional<User> exiting_user = userService.existingEmailCheck(user.getEmail());

        if (exiting_user.isPresent()){
            return new ResponseEntity("Email already has an Account!", HttpStatus.BAD_REQUEST);
        }

        User created_user = userService.createUser(user);

        if (created_user.getUserId() == null) {
            return new ResponseEntity("Registration failed", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Registration Successful User #" + created_user.getUserId(), HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody LoginInfo login){
        Optional<User> exiting_user = userService.existingEmailCheck(login.getEmail());

        if (!exiting_user.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        User user = exiting_user.get();

        if (!(user.getPassword().equals(login.getPassword()))){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userID}")
    public ResponseEntity<User> findUserByUserId(@PathVariable String userID) {
        User user = userService.findUserByUserId(userID);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateUserPreferences(@PathVariable("id") String userId, @RequestBody Preferences preferenceDto) {
        try {
            userService.updateUserPreferences(userId, preferenceDto);
            return new ResponseEntity<>("User preference updated successfully", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}

