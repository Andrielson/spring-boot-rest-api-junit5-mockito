package com.github.andrielson.spring.rest_api_junit_mockito.services.impl;

import com.github.andrielson.spring.rest_api_junit_mockito.domain.User;
import com.github.andrielson.spring.rest_api_junit_mockito.domain.dto.UserDto;
import com.github.andrielson.spring.rest_api_junit_mockito.repositories.UserRepository;
import com.github.andrielson.spring.rest_api_junit_mockito.services.UserService;
import com.github.andrielson.spring.rest_api_junit_mockito.services.exceptions.DataIntegrityViolationException;
import com.github.andrielson.spring.rest_api_junit_mockito.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDto userDto) {
        findByEmail(userDto);
        return userRepository.save(mapper.map(userDto, User.class));
    }

    private void findByEmail(UserDto userDto) {
//        var optionalUser = userRepository.findByEmail(userDto.getEmail());
//        if (optionalUser.isPresent()) {
//            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        userRepository.findByEmail(userDto.getEmail())
                .ifPresent(user -> {
                    throw new DataIntegrityViolationException("Email já cadastrado no sistema");
                });
    }
}
