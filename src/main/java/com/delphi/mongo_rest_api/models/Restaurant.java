package com.delphi.mongo_rest_api.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Restaurant {

    private int restaurantId;
    private String restaurant_name;
    private String address;
    private List<Item> menu_items;

    public Restaurant(String restaurant_name, String address, List<Item> menu_items) {
        this.restaurant_name = restaurant_name;
        this.address = address;
        this.menu_items = menu_items;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Item> getMenu_items() {
        return menu_items;
    }

    public void setMenu_items(List<Item> menu_items) {
        this.menu_items = menu_items;
    }
}
