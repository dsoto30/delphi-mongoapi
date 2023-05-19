package com.delphi.mongo_rest_api.controllers;


import com.delphi.mongo_rest_api.models.Restaurant;
import com.delphi.mongo_rest_api.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delphi/restaurants")
public class RestaurantController {


    @Autowired
    private RestaurantService restaurantService;
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
