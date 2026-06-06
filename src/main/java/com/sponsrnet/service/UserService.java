package com.sponsrnet.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sponsrnet.entity.User;
import com.sponsrnet.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create User
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Get All Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get User By Id
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Get User By Email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUser(
        Long id,
        User updatedUser) {

    User user =
        userRepository.findById(id)
        .orElseThrow(() ->
            new RuntimeException(
                "User not found"));

    user.setName(
        updatedUser.getName());

    user.setPhotoUrl(
        updatedUser.getPhotoUrl());

    if (updatedUser.getPhoneNumber() != null
            && !updatedUser.getPhoneNumber().isEmpty()
            && !updatedUser.getPhoneNumber()
                .matches("\\d{10}")) {

        throw new RuntimeException(
            "Phone number must contain exactly 10 digits");
    }

    user.setPhoneNumber(
        updatedUser.getPhoneNumber());

    return userRepository.save(user);
}
}