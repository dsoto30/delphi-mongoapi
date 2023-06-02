package com.delphi.mongo_rest_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.UUID;

@Data
@Document
public class Order {

    @Id
    private String orderId;
    private int restaurantId;
    private String userId;
    private ArrayList<Integer> ordered_items;

    public Order(int restaurantId, String userId, ArrayList<Integer> ordered_items) {
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.ordered_items = ordered_items;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
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
