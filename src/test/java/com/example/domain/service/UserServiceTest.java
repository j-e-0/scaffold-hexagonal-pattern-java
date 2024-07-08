package com.example.domain.service;

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
import com.example.domain.port.UserRepositoryPort;

public class UserServiceTest {

    private UserService userService;
    private UserRepositoryPort userRepository;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepositoryPort.class);
        userService = new UserService(userRepository);
    }

    @Test
    void shouldCreateUser() {
        User user = new User();
        user.setUsername("john");
        user.setEmail("john@example.com");
        user.setPassword("password");

        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals("john", createdUser.getUsername());
        assertEquals("john@example.com", createdUser.getEmail());
    }

    @Test
    void shouldGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setUsername("john");
        user.setEmail("john@example.com");
        user.setPassword("password");

        Mockito.when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.getUserById(1L);

        assertTrue(foundUser.isPresent());
        assertEquals("john", foundUser.get().getUsername());
    }

    @Test
    void shouldReturnEmptyIfUserNotExists() {
        Mockito.when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<User> foundUser = userService.getUserById(999L);

        assertFalse(foundUser.isPresent());
    }
}
