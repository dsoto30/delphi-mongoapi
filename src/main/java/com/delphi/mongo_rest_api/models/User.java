package com.delphi.mongo_rest_api.models;

import com.delphi.mongo_rest_api.models.Preferences;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.lang.reflect.Array;
import java.util.ArrayList;


@Data // Creates Class Constructor and Getters And Setters for us!
@Document
public class User {
    @Id
    @Field("_id")
    private String user_id;
    private String first_name, last_name;

    @Indexed(unique = true)
    private String email;

    private String password;
    private Preferences preferences;
    ArrayList<String> filters;

    public User(String first_name,
                String last_name,
                String email,
                String password,
                Preferences preferences,
                ArrayList<String> filters) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.preferences = preferences;
        this.filters = filters;
    }


}
