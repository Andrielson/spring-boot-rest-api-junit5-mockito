package com.github.andrielson.spring.rest_api_junit_mockito.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private String password;
}
