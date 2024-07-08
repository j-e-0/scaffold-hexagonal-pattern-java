package com.example.domain.port;

import java.util.List;
import java.util.Optional;

import com.example.domain.model.User;

public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    void deleteById(Long id);
}
