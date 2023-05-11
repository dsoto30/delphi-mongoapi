package com.delphi.mongo_rest_api.controllers;

import com.delphi.mongo_rest_api.models.Preferences;
import com.delphi.mongo_rest_api.models.User;
import com.delphi.mongo_rest_api.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("delphi/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity registerUser(
            @RequestParam("first_name") String first_name,
            @RequestParam("last_name") String last_name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("calories") int calories,
            @RequestParam("total_fat") int total_fat,
            @RequestParam("saturated_fat") int saturated_fat,
            @RequestParam("sodium") int sodium,
            @RequestParam("carbohydrates") int carbohydrates,
            @RequestParam("sugars") int sugars,
            @RequestParam("protein") int protein,
            @RequestParam("filters") ArrayList<String> filters){

        Optional<User> exiting_user = userService.existingEmailCheck(email);

        if (exiting_user.isPresent()){
            return new ResponseEntity("Email already has an Account!", HttpStatus.BAD_REQUEST);
        }
        Preferences user_preferences = new Preferences(calories,
                total_fat, saturated_fat, sodium, carbohydrates, sugars, protein);

        ArrayList<String> user_filters = filters;
        User user = new User(
                first_name,
                last_name,
                email,
                password,
                user_preferences,
                filters);


        User created_user = userService.createUser(user);

        if (created_user.getUser_id() == null) {
            return new ResponseEntity("Registration failed", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Registration Successful User #" + created_user.getUser_id(), HttpStatus.OK);
    }


    @PostMapping("/login")
    public User loginUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        Optional<User> exiting_user = userService.existingEmailCheck(email);

        if (!exiting_user.isPresent()) {
            return null;
        }

        User user = exiting_user.get();

        if (!(user.getPassword().equals(password))){
            return null;
        }

        return user;
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "testEndPoint() works!!";
    }
}
