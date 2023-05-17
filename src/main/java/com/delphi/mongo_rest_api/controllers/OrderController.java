package com.delphi.mongo_rest_api.controllers;


import com.delphi.mongo_rest_api.models.Order;
import com.delphi.mongo_rest_api.models.Recommendation;
import com.delphi.mongo_rest_api.models.User;
import com.delphi.mongo_rest_api.services.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        if (created_order.getUserId() == null) {
            return new ResponseEntity("Registration failed", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Registration Successful User #" + created_order.getUserId() + ", Restaurant #" + created_order.getRestaurantId() + ", Order #" + created_order.getOrderId(), HttpStatus.OK);
    }

    @GetMapping("/recommend/{userId}/{restaurantId}")
    public ResponseEntity<String> getRecommendations(
            @PathVariable String userId,
            @PathVariable String restaurantId){

        return new ResponseEntity(callPythonScript(restaurantId, userId),HttpStatus.OK);
    }

    private static String callPythonScript(String param1, String param2) {
        try {
            // Build command to execute Python script with parameters
            String pythonPath = "/home/ec2-user/del_menuAI.py";
            List<String> command = Arrays.asList("python", pythonPath, param1, param2);

            // Execute command and capture output
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            InputStream errorStream = process.getErrorStream();

            // Read output from Python script
            StringBuilder output = new StringBuilder();
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Read error output from Python script
            StringBuilder errorOutput = new StringBuilder();
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
            while ((line = errorReader.readLine()) != null) {
                errorOutput.append(line).append("\n");
            }

            // Wait for the process to finish
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                if (errorOutput.length() > 0) {
                    System.out.println("Warning: Error output from Python script:");
                    System.out.println(errorOutput.toString());
                }
                return output.toString();
            } else {
                System.out.println("Error: Python script execution failed with exit code " + exitCode);
                if (errorOutput.length() > 0) {
                    System.out.println("Error output from Python script:");
                    System.out.println(errorOutput.toString());
                }
                return null; // or handle the error case as desired
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
