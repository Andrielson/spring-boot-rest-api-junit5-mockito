package com.github.andrielson.spring.rest_api_junit_mockito.services;

import com.github.andrielson.spring.rest_api_junit_mockito.domain.User;

public interface UserService {

    User findById(Integer id);
}
