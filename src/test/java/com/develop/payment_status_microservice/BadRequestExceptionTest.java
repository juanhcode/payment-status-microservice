package com.develop.payment_status_microservice;
import com.develop.payment_status_microservice.presentation.exceptions.BadRequestException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BadRequestExceptionTest {

    @Test
    void shouldThrowBadRequestExceptionWithMessage() {
        String errorMessage = "Invalid input data";

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            throw new BadRequestException(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage());
    }
}
