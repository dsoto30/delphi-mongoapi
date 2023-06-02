package com.delphi.mongo_rest_api.controllers;


import com.delphi.mongo_rest_api.RecommendationParser;
import com.delphi.mongo_rest_api.models.Order;
import com.delphi.mongo_rest_api.models.Recommendation;
import com.delphi.mongo_rest_api.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.delphi.mongo_rest_api.CallPythonScript.RecommendScript;
import static com.delphi.mongo_rest_api.CallPythonScript.UpdateScript;

@RestController
@RequestMapping("/delphi/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @GetMapping("/{orderID}")
    public ResponseEntity<Order> getOrderByID(@PathVariable String orderID) {
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
        String orderId = created_order.getOrderId();
        if (created_order.getOrderId() == null) {
            return new ResponseEntity("Registration failed", HttpStatus.BAD_REQUEST);
        }
        String pythonScriptResult = UpdateScript(orderId);

        return new ResponseEntity("Registration Successful User #" + created_order.getUserId() + ", Restaurant #" + created_order.getRestaurantId() + ", Order #" + created_order.getOrderId()
                + "\n" + pythonScriptResult, HttpStatus.OK);
    }

    @GetMapping("/recommend/{userId}/{restaurantId}")
    public ResponseEntity<Map<String, List<Recommendation>>> getRecommendations(
            @PathVariable String userId,
            @PathVariable String restaurantId) {

        String pythonScriptResult = RecommendScript(restaurantId, userId);
        List<Recommendation> recommendations = RecommendationParser.parseString(pythonScriptResult);

        Map<String, List<Recommendation>> response = new HashMap<>();
        response.put("Recommendations", recommendations);

        return ResponseEntity.ok(response);
    }


}
