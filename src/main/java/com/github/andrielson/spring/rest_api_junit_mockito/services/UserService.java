package com.github.andrielson.spring.rest_api_junit_mockito.services;

import com.github.andrielson.spring.rest_api_junit_mockito.domain.User;
import com.github.andrielson.spring.rest_api_junit_mockito.domain.dto.UserDto;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();

    User create(UserDto userDto);
}
