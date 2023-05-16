package com.delphi.mongo_rest_api.services;


import com.delphi.mongo_rest_api.models.Order;
import com.delphi.mongo_rest_api.models.User;
import com.delphi.mongo_rest_api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order findByOrderId(Integer id){
        return orderRepository.findByOrderId(id);
    }

    public Order createOrder(Order order) {
        return orderRepository.insert(order);
    }

}
