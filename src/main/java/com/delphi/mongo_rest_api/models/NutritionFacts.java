package com.delphi.mongo_rest_api.models;


public class NutritionFacts {
    private double serving_size;
    private double calories;
    private double calories_from_fat;
    private double total_fat;
    private double saturated_fat;
    private double trans_fat;
    private double cholesterol;
    private double sodium;
    private double carbohydrates;
    private double dietary_fiber;
    private double sugars;
    private double protein;

    public NutritionFacts(){};
    public NutritionFacts(double serving_size, double calories, double calories_from_fat, double total_fat, double saturated_fat, double trans_fat, double cholesterol, double sodium, double carbohydrates, double dietary_fiber, double sugars, double protein) {
        this.serving_size = serving_size;
        this.calories = calories;
        this.calories_from_fat = calories_from_fat;
        this.total_fat = total_fat;
        this.saturated_fat = saturated_fat;
        this.trans_fat = trans_fat;
        this.cholesterol = cholesterol;
        this.sodium = sodium;
        this.carbohydrates = carbohydrates;
        this.dietary_fiber = dietary_fiber;
        this.sugars = sugars;
        this.protein = protein;
    }

    public double getServing_size() {
        return serving_size;
    }

    public void setServing_size(double serving_size) {
        this.serving_size = serving_size;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getCalories_from_fat() {
        return calories_from_fat;
    }

    public void setCalories_from_fat(double calories_from_fat) {
        this.calories_from_fat = calories_from_fat;
    }

    public double getTotal_fat() {
        return total_fat;
    }

    public void setTotal_fat(double total_fat) {
        this.total_fat = total_fat;
    }

    public double getSaturated_fat() {
        return saturated_fat;
    }

    public void setSaturated_fat(double saturated_fat) {
        this.saturated_fat = saturated_fat;
    }

    public double getTrans_fat() {
        return trans_fat;
    }

    public void setTrans_fat(double trans_fat) {
        this.trans_fat = trans_fat;
    }

    public double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getDietary_fiber() {
        return dietary_fiber;
    }

    public void setDietary_fiber(double dietary_fiber) {
        this.dietary_fiber = dietary_fiber;
    }

    public double getSugars() {
        return sugars;
    }

    public void setSugars(double sugars) {
        this.sugars = sugars;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }
}
