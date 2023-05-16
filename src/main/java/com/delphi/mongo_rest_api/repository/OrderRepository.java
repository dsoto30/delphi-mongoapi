package com.delphi.mongo_rest_api.repository;
import com.delphi.mongo_rest_api.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
    Order findByOrderId(String id);

}
