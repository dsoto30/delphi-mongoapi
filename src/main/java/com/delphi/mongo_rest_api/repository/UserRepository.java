package com.delphi.mongo_rest_api.repository;

import com.delphi.mongo_rest_api.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    Optional<User> findUserByEmail(String email);
}
