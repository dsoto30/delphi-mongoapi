package com.delphi.mongo_rest_api.models;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Recommendation {
    private int rank;
    private String menu_item_name;
}
