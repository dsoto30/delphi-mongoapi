package com.delphi.mongo_rest_api.controllers;

import com.delphi.mongo_rest_api.models.Order;
import com.delphi.mongo_rest_api.models.Preferences;
import com.delphi.mongo_rest_api.models.Recommendation;
import com.delphi.mongo_rest_api.models.User;
import com.delphi.mongo_rest_api.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
            @RequestParam("filters") List<String> filters){

        Optional<User> exiting_user = userService.existingEmailCheck(email);

        if (exiting_user.isPresent()){
            return new ResponseEntity("Email already has an Account!", HttpStatus.BAD_REQUEST);
        }

        Preferences user_preferences = new Preferences(calories,
                total_fat, saturated_fat, sodium, carbohydrates, sugars, protein);

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

    @GetMapping("recommend")
    public List<Recommendation> getRecommendations(
            @RequestParam("user_id") String user_id,
            @RequestParam("restaurant_id") String restaurant_id){

        return callPythonScript(user_id, restaurant_id);
    }

    private List<Recommendation> callPythonScript(String param1, String param2) {
        try {
            // Build command to execute Python script with parameters
            String python_path = "C:\\Users\\sotod\\OneDrive\\Documents\\Delphi\\RecommenderSystem\\del_menuAI.py";
            List<String> command = Arrays.asList("python", python_path, param1, param2);

            // Execute command and capture output
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Read output from Python script
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            // Parse output into List<Recommendation> objects
            ObjectMapper objectMapper = new ObjectMapper();
            List<Recommendation> recommendations = objectMapper.readValue(output.toString(), new TypeReference<List<Recommendation>>() {});

            return recommendations;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("/update-preferences")
    public ResponseEntity updatePreferences(@RequestBody Order order){
        return new ResponseEntity("Updated User # " + order.getUser_id() + " preferences", HttpStatus.OK);
    }

}

