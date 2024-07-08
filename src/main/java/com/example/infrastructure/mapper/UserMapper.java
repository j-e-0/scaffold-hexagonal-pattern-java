package com.example.infrastructure.mapper;

import com.example.domain.model.User;
import com.example.infrastructure.entity.UserEntity;

public class UserMapper {

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        return entity;
    }

    public static User toModel(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        return user;
    }
}
