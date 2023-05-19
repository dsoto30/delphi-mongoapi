package com.delphi.mongo_rest_api.models;

import lombok.Data;

import java.util.List;

@Data

public class Item {


    private int item_id;
    private String category;
    private String item_name;
    private NutritionFacts nutritionFacts;
    private List<String> ingredients;

    public Item(int item_id, String category, String item_name, NutritionFacts nutritionFacts, List<String> ingredients) {
        this.item_id = item_id;
        this.category = category;
        this.item_name = item_name;
        this.nutritionFacts = nutritionFacts;
        this.ingredients = ingredients;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public NutritionFacts getNutritionFacts() {
        return nutritionFacts;
    }

    public void setNutritionFacts(NutritionFacts nutritionFacts) {
        this.nutritionFacts = nutritionFacts;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
