package com.example.application.controller;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.domain.model.User;
import com.example.domain.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {
        Mockito.reset(userService);
    }

    @Test
    void shouldCreateUser() throws Exception {
        User user = new User();
        user.setUsername("john");
        user.setEmail("john@example.com");
        user.setPassword("password");

        Mockito.when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"john\",\"email\":\"john@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("john"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    void shouldGetUserById() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("john");
        user.setEmail("john@example.com");
        user.setPassword("password");

        Mockito.when(userService.getUserById(anyLong())).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("john"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    void shouldReturnNotFoundIfUserNotExists() throws Exception {
        Mockito.when(userService.getUserById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/users/999"))
                .andExpect(status().isNotFound());
    }
}
