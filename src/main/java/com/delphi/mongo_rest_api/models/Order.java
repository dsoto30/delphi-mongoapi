package com.delphi.mongo_rest_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Order {
    private String user_id, restaurant_id;
    private List<Integer> ordered_items;
}
