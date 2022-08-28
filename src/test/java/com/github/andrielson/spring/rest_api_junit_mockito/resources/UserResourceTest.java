package com.github.andrielson.spring.rest_api_junit_mockito.resources;

import com.github.andrielson.spring.rest_api_junit_mockito.domain.User;
import com.github.andrielson.spring.rest_api_junit_mockito.domain.dto.UserDto;
import com.github.andrielson.spring.rest_api_junit_mockito.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
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
    void whenFindByIdThenReturnSuccess() {
        when(userService.findById(anyInt())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDto);

        var response = userResource.findById(user.getId());

        assertNotNull(response);
        assertTrue(response.hasBody());
        assertEquals(ResponseEntity.class, response.getClass());
        var responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(UserDto.class, responseBody.getClass());
        assertEquals(userDto.getId(), responseBody.getId());
        assertEquals(userDto.getName(), responseBody.getName());
        assertEquals(userDto.getEmail(), responseBody.getEmail());
        assertEquals(userDto.getPassword(), responseBody.getPassword());
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