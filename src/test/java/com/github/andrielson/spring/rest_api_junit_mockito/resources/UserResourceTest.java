package com.github.andrielson.spring.rest_api_junit_mockito.resources;

import com.github.andrielson.spring.rest_api_junit_mockito.domain.User;
import com.github.andrielson.spring.rest_api_junit_mockito.domain.dto.UserDto;
import com.github.andrielson.spring.rest_api_junit_mockito.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

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
    void whenFindByIdThenReturnsSuccess() {
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
    }

    @Test
    void whenFindAllThenReturnsAListOfUserDto() {
        when(userService.findAll()).thenReturn(List.of(user));
        when(mapper.map(any(), any())).thenReturn(userDto);

        var response = userResource.findAll();
        assertNotNull(response);
        assertTrue(response.hasBody());
        assertEquals(ResponseEntity.class, response.getClass());

        var responseBody = response.getBody();
        assertNotNull(responseBody);

        var firstUserDto = responseBody.get(0);
        assertEquals(UserDto.class, firstUserDto.getClass());
        assertEquals(userDto.getId(), firstUserDto.getId());
        assertEquals(userDto.getName(), firstUserDto.getName());
        assertEquals(userDto.getEmail(), firstUserDto.getEmail());
    }

    @Test
    void whenCreateThenReturnsCreated() {
        when(userService.create(any())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDto);

        var response = userResource.create(userDto);
        assertNotNull(response);
        assertTrue(response.hasBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        var responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(UserDto.class, responseBody.getClass());
        assertEquals(userDto.getId(), responseBody.getId());
        assertEquals(userDto.getName(), responseBody.getName());
        assertEquals(userDto.getEmail(), responseBody.getEmail());
    }

    @Test
    void whenUpdateThenReturnsSuccess() {
        when(userService.update(any())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDto);

        var response = userResource.update(user.getId(), userDto);
        assertNotNull(response);
        assertTrue(response.hasBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        var responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(UserDto.class, responseBody.getClass());
        assertEquals(userDto.getId(), responseBody.getId());
        assertEquals(userDto.getName(), responseBody.getName());
        assertEquals(userDto.getEmail(), responseBody.getEmail());
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