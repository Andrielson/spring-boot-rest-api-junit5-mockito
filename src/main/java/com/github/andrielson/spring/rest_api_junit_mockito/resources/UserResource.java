package com.github.andrielson.spring.rest_api_junit_mockito.resources;

import com.github.andrielson.spring.rest_api_junit_mockito.domain.dto.UserDto;
import com.github.andrielson.spring.rest_api_junit_mockito.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserResource {

    private final UserService userService;
    private final ModelMapper mapper;

    @GetMapping("{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Integer id) {
        var user = userService.findById(id);
        return ResponseEntity.ok().body(mapper.map(user, UserDto.class));
    }
}
