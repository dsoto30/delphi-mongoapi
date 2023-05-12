package com.delphi.mongo_rest_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Order {
    private String user_id, restaurant_id;
    private ArrayList<Integer> ordered_items;
}
