package com.delphi.mongo_rest_api.repository;

import com.delphi.mongo_rest_api.models.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant,String> {
    Restaurant findRestaurantByRestaurantId(int id);
}
