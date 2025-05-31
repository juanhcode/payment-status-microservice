package com.develop.payment_status_microservice;


import com.develop.payment_status_microservice.presentation.exceptions.BadRequestException;
import com.develop.payment_status_microservice.presentation.exceptions.RestResponseEntityExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestResponseEntityExceptionHandlerTest {

    @Test
    void handleBadRequest_ShouldReturnBadRequestResponse() {
        // Arrange
        RestResponseEntityExceptionHandler handler = new RestResponseEntityExceptionHandler();
        BadRequestException ex = new BadRequestException("Invalid input");
        WebRequest request = mock(WebRequest.class);

        // Act
        ResponseEntity<Object> response = handler.handleBadRequest(ex, request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());

        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertEquals("Invalid input", body.get("message"));
        assertEquals("400 BAD_REQUEST", body.get("Error"));
        assertNotNull(body.get("timestamp"));
    }
}
