package com.github.andrielson.spring.rest_api_junit_mockito.resources;

import com.github.andrielson.spring.rest_api_junit_mockito.domain.User;
import com.github.andrielson.spring.rest_api_junit_mockito.domain.dto.UserDto;
import com.github.andrielson.spring.rest_api_junit_mockito.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import static org.mockito.MockitoAnnotations.openMocks;

class UserResourceTest {

    private UserResource userResource;

    @Mock
    private UserService userService;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        openMocks(this);
        userResource = new UserResource(userService, mapper);
        startUser();
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        var id = 1;
        var name = "User One";
        var email = "user_one@mail.com";
        var password = "password";

        user = new User(id, name, email, password);
        userDto = new UserDto(id, name, email, password);
    }
}