package com.delphi.mongo_rest_api.services;

import com.delphi.mongo_rest_api.UserNotFoundException;
import com.delphi.mongo_rest_api.models.Preferences;
import com.delphi.mongo_rest_api.models.User;
import com.delphi.mongo_rest_api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private MongoOperations mongoTemplate;

    public User createUser(User user) {
        return userRepository.insert(user);
    }

    public Optional<User> existingEmailCheck(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User findUserByUserId(String id){
        return userRepository.findByUserId(id);
    }

    public void updateUserPreferences(String userId, Preferences preference) throws UserNotFoundException {
        User user = findUserByUserId(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        Preferences preferences = new Preferences(preference.getCalories(), preference.getTotal_fat(), preference.getSaturated_fat(), preference.getSodium(), preference.getCarbohydrates(), preference.getSugars(), preference.getProtein());
        user.setPreferences(preferences);

        Query query = new Query(Criteria.where("userId").is(userId));
        Update update = new Update();
        update.set("preferences", preferences);
        mongoTemplate.updateFirst(query, update, User.class);
    }
}
