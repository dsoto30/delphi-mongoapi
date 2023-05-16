package com.delphi.mongo_rest_api;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String id) {
        super("User not found with id : " + id);
    }
}

