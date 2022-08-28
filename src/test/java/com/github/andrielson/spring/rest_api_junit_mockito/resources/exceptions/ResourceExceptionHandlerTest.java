package com.github.andrielson.spring.rest_api_junit_mockito.resources.exceptions;

import com.github.andrielson.spring.rest_api_junit_mockito.services.exceptions.DataIntegrityViolationException;
import com.github.andrielson.spring.rest_api_junit_mockito.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

class ResourceExceptionHandlerTest {

    public static final String OBJECT_NOT_FOUND_MESSAGE = "Objeto não encontrado";
    public static final String DATA_INTEGRITY_VIOLATION_MESSAGE = "E-mail já cadastrado no sistema";
    private ResourceExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        exceptionHandler = new ResourceExceptionHandler();
    }

    @Test
    void objectNotFound() {
        var response = exceptionHandler.objectNotFound(new ObjectNotFoundException(OBJECT_NOT_FOUND_MESSAGE), new MockHttpServletRequest());

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertTrue(response.hasBody());

        var responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(OBJECT_NOT_FOUND_MESSAGE, responseBody.getError());
        assertEquals(404, responseBody.getStatus());
        assertNotNull(responseBody.getPath());
        assertNotNull(responseBody.getTimestamp());
    }

    @Test
    void dataIntegrityViolation() {
        var response = exceptionHandler.dataIntegrityViolation(new DataIntegrityViolationException(DATA_INTEGRITY_VIOLATION_MESSAGE), new MockHttpServletRequest());

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertTrue(response.hasBody());

        var responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(DATA_INTEGRITY_VIOLATION_MESSAGE, responseBody.getError());
        assertEquals(400, responseBody.getStatus());
        assertNotNull(responseBody.getPath());
        assertNotNull(responseBody.getTimestamp());
    }
}