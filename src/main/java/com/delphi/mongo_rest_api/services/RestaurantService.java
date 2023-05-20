package com.delphi.mongo_rest_api.services;


import com.delphi.mongo_rest_api.models.Restaurant;
import com.delphi.mongo_rest_api.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant getRestaurantById(Integer restaurantID) {
        return restaurantRepository.findRestaurantByRestaurantId(restaurantID);
    }
    public Restaurant createRestaurant(Restaurant restaurant){
        return restaurantRepository.insert(restaurant);
    }
}
