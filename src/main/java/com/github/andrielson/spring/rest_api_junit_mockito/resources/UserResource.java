package com.github.andrielson.spring.rest_api_junit_mockito.resources;

import com.github.andrielson.spring.rest_api_junit_mockito.domain.dto.UserDto;
import com.github.andrielson.spring.rest_api_junit_mockito.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserResource {

    public static final String ID = "{id}";
    private final UserService userService;
    private final ModelMapper mapper;

    @GetMapping(ID)
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
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(newUser, UserDto.class));
    }

    @PutMapping(ID)
    public ResponseEntity<UserDto> update(@PathVariable Integer id, @RequestBody UserDto userDto) {
        userDto.setId(id);
        return ResponseEntity.ok(mapper.map(userService.update(userDto), UserDto.class));
    }

    @DeleteMapping(ID)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
