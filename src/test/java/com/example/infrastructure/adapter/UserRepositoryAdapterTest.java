package com.example.infrastructure.adapter;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.Mockito;

import com.example.domain.model.User;
import com.example.infrastructure.entity.UserEntity;
import com.example.infrastructure.repository.UserJpaRepository;

public class UserRepositoryAdapterTest {

    private UserRepositoryAdapter userRepositoryAdapter;
    private UserJpaRepository userJpaRepository;

    @BeforeEach
    void setUp() {
        userJpaRepository = Mockito.mock(UserJpaRepository.class);
        userRepositoryAdapter = new UserRepositoryAdapter(userJpaRepository);
    }

    @Test
    void shouldSaveUser() {
        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setUsername("john");
        entity.setEmail("john@example.com");
        entity.setPassword("password");

        Mockito.when(userJpaRepository.save(any(UserEntity.class))).thenReturn(entity);

        User user = new User();
        user.setUsername("john");
        user.setEmail("john@example.com");
        user.setPassword("password");

        User savedUser = userRepositoryAdapter.save(user);

        assertNotNull(savedUser);
        assertEquals("john", savedUser.getUsername());
        assertEquals("john@example.com", savedUser.getEmail());
    }

    @Test
    void shouldFindUserById() {
        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setUsername("john");
        entity.setEmail("john@example.com");
        entity.setPassword("password");

        Mockito.when(userJpaRepository.findById(anyLong())).thenReturn(Optional.of(entity));

        Optional<User> foundUser = userRepositoryAdapter.findById(1L);

        assertTrue(foundUser.isPresent());
        assertEquals("john", foundUser.get().getUsername());
    }

    @Test
    void shouldReturnEmptyIfUserNotExists() {
        Mockito.when(userJpaRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<User> foundUser = userRepositoryAdapter.findById(999L);

        assertFalse(foundUser.isPresent());
    }
}
