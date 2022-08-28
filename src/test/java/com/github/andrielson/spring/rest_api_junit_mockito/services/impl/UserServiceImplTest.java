package com.github.andrielson.spring.rest_api_junit_mockito.services.impl;

import com.github.andrielson.spring.rest_api_junit_mockito.domain.User;
import com.github.andrielson.spring.rest_api_junit_mockito.domain.dto.UserDto;
import com.github.andrielson.spring.rest_api_junit_mockito.repositories.UserRepository;
import com.github.andrielson.spring.rest_api_junit_mockito.services.exceptions.DataIntegrityViolationException;
import com.github.andrielson.spring.rest_api_junit_mockito.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class UserServiceImplTest {

    public static final String OBJECT_NOT_FOUND_MESSAGE = "Objeto não encontrado";
    public static final String DATA_INTEGRITY_VIOLATION_MESSAGE = "E-mail já cadastrado no sistema";
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDto userDto;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        openMocks(this);
        userService = new UserServiceImpl(userRepository, mapper);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnsAnUserInstance() {
        when(userRepository.findById(anyInt())).thenReturn(optionalUser);
        var response = userService.findById(user.getId());

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(user.getId(), response.getId());
        assertEquals(user.getName(), response.getName());
        assertEquals(user.getEmail(), response.getEmail());
        assertEquals(user.getPassword(), response.getPassword());
    }

    @Test
    void whenFindByIdThenReturnsAnObjectNotFoundException() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
        try {
            userService.findById(user.getId());
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJECT_NOT_FOUND_MESSAGE, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnsAListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        var response = userService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        var firstUser = response.get(0);
        assertEquals(User.class, firstUser.getClass());
        assertEquals(user.getId(), firstUser.getId());
        assertEquals(user.getName(), firstUser.getName());
        assertEquals(user.getEmail(), firstUser.getEmail());
        assertEquals(user.getPassword(), firstUser.getPassword());
    }

    @Test
    void whenCreateThenReturnsSuccess() {
        when(userRepository.save(any())).thenReturn(user);
        var response = userService.create(userDto);
        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(user.getId(), response.getId());
        assertEquals(user.getName(), response.getName());
        assertEquals(user.getEmail(), response.getEmail());
        assertEquals(user.getPassword(), response.getPassword());
    }

    @Test
    void whenCreateThenReturnsDataIntegrityViolationException() {
        optionalUser.get().setId(2);
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);
        try {
            userService.create(userDto);
        } catch (Exception exception) {
            assertEquals(DataIntegrityViolationException.class, exception.getClass());
            assertEquals(DATA_INTEGRITY_VIOLATION_MESSAGE, exception.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnsSuccess() {
        when(userRepository.save(any())).thenReturn(user);
        var response = userService.update(userDto);
        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(user.getId(), response.getId());
        assertEquals(user.getName(), response.getName());
        assertEquals(user.getEmail(), response.getEmail());
        assertEquals(user.getPassword(), response.getPassword());
    }

    @Test
    void whenUpdateThenReturnsDataIntegrityViolationException() {
        optionalUser.get().setId(2);
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);
        try {
            userService.update(userDto);
        } catch (Exception exception) {
            assertEquals(DataIntegrityViolationException.class, exception.getClass());
            assertEquals(DATA_INTEGRITY_VIOLATION_MESSAGE, exception.getMessage());
        }
    }

    @Test
    void whenDeleteReturnsSuccess() {
        when(userRepository.findById(anyInt())).thenReturn(optionalUser);
        doNothing().when(userRepository).deleteById(anyInt());
        userService.delete(user.getId());
        verify(userRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void whenDeleteReturnsObjectNotFoundException() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
        try {
            userService.delete(user.getId());
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJECT_NOT_FOUND_MESSAGE, ex.getMessage());
            verify(userRepository, times(0)).deleteById(anyInt());
        }
    }

    private void startUser() {
        var id = 1;
        var name = "User One";
        var email = "user_one@mail.com";
        var password = "password";

        user = new User(id, name, email, password);
        userDto = new UserDto(id, name, email, password);
        optionalUser = Optional.of(new User(id, name, email, password));
    }
}