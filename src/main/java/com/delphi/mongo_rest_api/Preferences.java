package com.delphi.mongo_rest_api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Preferences {
    private int calories, total_fat, saturated_fat, sodium, carbohydrates, sugars, protein;
}
