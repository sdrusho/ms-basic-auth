package org.basic.microservice.service;

import org.basic.microservice.model.User;
import org.basic.microservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }

    public List<User> getUsersByUserName(String userName) {
        return userRepository.getUserByName(userName);
    }
}
