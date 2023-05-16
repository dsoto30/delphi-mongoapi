package com.delphi.mongo_rest_api.controllers;


import com.delphi.mongo_rest_api.models.Order;
import com.delphi.mongo_rest_api.models.User;
import com.delphi.mongo_rest_api.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delphi/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @GetMapping("/{orderID}")
    public ResponseEntity<Order> getOrderByID(@PathVariable Integer orderID) {
        Order order = orderService.findByOrderId(orderID);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity registerOrder(@RequestBody Order order){
        Order created_order = orderService.createOrder(order);
        if (created_order.getUserId() == null) {
            return new ResponseEntity("Registration failed", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Registration Successful User #" + created_order.getUserId() + " Restaurant #" + created_order.getRestaurantId() + "Order #" + created_order.getOrderId(), HttpStatus.OK);
    }
}
