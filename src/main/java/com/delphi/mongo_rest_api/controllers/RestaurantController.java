package com.delphi.mongo_rest_api.controllers;


import com.delphi.mongo_rest_api.models.Restaurant;
import com.delphi.mongo_rest_api.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/delphi/restaurants")
public class RestaurantController {


    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/create")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant){
        Restaurant existing_restaurant = restaurantService.getRestaurantById(restaurant.getRestaurantId());

        if (existing_restaurant != null)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else
        {
            return new ResponseEntity<>(restaurantService.createRestaurant(restaurant), HttpStatus.OK);
        }
    }

    @GetMapping("/{restaurantID}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Integer restaurantID) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantID);
        if (restaurant != null) {
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
