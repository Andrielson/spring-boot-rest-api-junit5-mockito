package com.github.andrielson.spring.rest_api_junit_mockito.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class StandardError {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;
}
