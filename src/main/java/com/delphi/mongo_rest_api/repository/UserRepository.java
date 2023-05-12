package com.delphi.mongo_rest_api.repository;

import com.delphi.mongo_rest_api.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findUserByEmail(String email);
}
