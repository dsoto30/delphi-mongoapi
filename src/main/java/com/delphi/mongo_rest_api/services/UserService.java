package com.delphi.mongo_rest_api.services;

import com.delphi.mongo_rest_api.models.User;
import com.delphi.mongo_rest_api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.insert(user);
    }

    public Optional<User> existingEmailCheck(String email) {
        return userRepository.findUserByEmail(email);
    }
}
