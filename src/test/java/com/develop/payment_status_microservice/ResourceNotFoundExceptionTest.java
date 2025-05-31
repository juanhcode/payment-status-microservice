package com.develop.payment_status_microservice;

import com.develop.payment_status_microservice.presentation.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceNotFoundExceptionTest {

    @Test
    void shouldThrowResourceNotFoundExceptionWithMessage() {
        String errorMessage = "Resource with ID 123 not found";

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            throw new ResourceNotFoundException(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage());
    }
}
