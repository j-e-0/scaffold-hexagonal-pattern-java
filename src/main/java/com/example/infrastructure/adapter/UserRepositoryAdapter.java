package com.example.infrastructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.model.User;
import com.example.domain.port.UserRepositoryPort;
import com.example.infrastructure.entity.UserEntity;
import com.example.infrastructure.mapper.UserMapper;
import com.example.infrastructure.repository.UserJpaRepository;

@Service
public class UserRepositoryAdapter implements UserRepositoryPort {

    private UserJpaRepository userJpaRepository;

    @Autowired
    public UserRepositoryAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        UserEntity savedEntity = userJpaRepository.save(entity);
        return UserMapper.toModel(savedEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<UserEntity> entity = userJpaRepository.findById(id);
        return entity.map(UserMapper::toModel);
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll().stream().map(UserMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        userJpaRepository.deleteById(id);
    }
}
