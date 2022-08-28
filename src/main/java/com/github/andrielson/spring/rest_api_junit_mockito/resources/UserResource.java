package com.github.andrielson.spring.rest_api_junit_mockito.resources;

import com.github.andrielson.spring.rest_api_junit_mockito.domain.dto.UserDto;
import com.github.andrielson.spring.rest_api_junit_mockito.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll()
                .stream().map(user -> mapper.map(user, UserDto.class))
                .toList());
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        var newUser = userService.create(userDto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(uri).body(mapper.map(newUser, UserDto.class));
    }
}
