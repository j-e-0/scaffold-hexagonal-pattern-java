package com.example.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.model.User;
import com.example.domain.port.UserRepositoryPort;

@Service
public class UserService {

    private UserRepositoryPort userRepository;

    @Autowired
    public UserService(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    public void setUserRepository(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
