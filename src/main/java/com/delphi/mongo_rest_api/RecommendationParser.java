package com.delphi.mongo_rest_api;

import com.delphi.mongo_rest_api.models.Recommendation;

import java.util.ArrayList;
import java.util.List;

public class RecommendationParser {
    public static List<Recommendation> parseString(String input) {
        List<Recommendation> recommendations = new ArrayList<>();

        String[] lines = input.split("\n");
        for (int i = 0; i < lines.length; i++) {
            Recommendation recommendation = parseLine(lines[i], i + 1);
            if (recommendation != null) {
                recommendations.add(recommendation);
            }
        }

        return recommendations;
    }

    private static Recommendation parseLine(String line, int rank) {
        Recommendation recommendation = new Recommendation(rank, null, null);
        String[] fields = line.split("item_name:");

        for (String field : fields) {
            if (field.startsWith("item_id:")) {
                String itemIdString = field.substring(9).trim();
                if (!itemIdString.isEmpty()) {
                    recommendation.setItemId(Integer.parseInt(itemIdString));
                }
            } else if (!field.equals("")) {
                String itemName = field.substring(1).trim();
                if (!itemName.isEmpty()) {
                    recommendation.setItemName(itemName);
                }
            }
        }

        return recommendation;
    }

    public static void main(String[] args) {
        String input = "item_id: 157 item_name: McFlurry with Reese's Peanut Butter Cups\n" +
                "item_id: 156 item_name: McFlurry with Oreo Cookies\n" +
                "item_id: 108 item_name: Hot Fudge Sundae\n" +
                "item_id: 155 item_name: McFlurry with M&M'S Candies\n" +
                "item_id: 107 item_name: Kids Ice Cream Cone\n" +
                "item_id: 153 item_name: Chocolate Shake\n" +
                "item_id: 77 item_name: Premium McWrap Chicken Sweet Chili (Crispy Chicken)\n" +
                "item_id: 36 item_name: Hotcakes\n" +
                "item_id: 152 item_name: Strawberry Shake\n" +
                "item_id: 110 item_name: Strawberry Sundae";


        List<Recommendation> recommendations = RecommendationParser.parseString(input);
        for (Recommendation rec : recommendations)
        {
            System.out.println(rec.getItemName());
        }
        for (Recommendation rec : recommendations)
        {
            System.out.println(rec.getItemId());
        }

    }
}
