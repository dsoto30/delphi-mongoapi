package com.delphi.mongo_rest_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Order {

    private int orderId,restaurantId;
    private String userId;
    private ArrayList<Integer> ordered_items;


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurantId = restaurant_id;
    }

    public ArrayList<Integer> getOrdered_items() {
        return ordered_items;
    }

    public void setOrdered_items(ArrayList<Integer> ordered_items) {
        this.ordered_items = ordered_items;
    }
}
