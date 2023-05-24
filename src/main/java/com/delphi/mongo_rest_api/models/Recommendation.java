package com.delphi.mongo_rest_api.models;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class Recommendation {
    private Integer rank;
    private Integer item_id;
    private String item_name;

    // Constructor
    public Recommendation(Integer rank, Integer item_id, String item_name) {
        this.rank = rank;
        this.item_id = item_id;
        this.item_name = item_name;
    }

    // Getters and setters

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getItemId() {
        return item_id;
    }

    public void setItemId(Integer item_id) {
        this.item_id = item_id;
    }

    public String getItemName() {
        return item_name;
    }

    public void setItemName(String item_name) {
        this.item_name = item_name;
    }
}

