package com.develop.payment_status_microservice;

import com.develop.payment_status_microservice.domain.interfaces.PaymentStatusService;
import com.develop.payment_status_microservice.domain.models.PaymentStatus;
import com.develop.payment_status_microservice.presentation.controllers.PaymentStatusControllers;
import com.develop.payment_status_microservice.presentation.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentStatusControllersTest {

    @Mock
    private PaymentStatusService paymentStatusService;

    @InjectMocks
    private PaymentStatusControllers paymentStatusControllers;

    private PaymentStatus sampleStatus;

    @BeforeEach
    void setup() {
        sampleStatus = new PaymentStatus();
        sampleStatus.setId(1);
        sampleStatus.setName("PAID");
    }

    @Test
    void getPaymentStatusNameById_WhenFound_ShouldReturn200() {
        // Arrange
        when(paymentStatusService.getPaymentStatusNameById(1)).thenReturn(Optional.of(sampleStatus));

        // Act
        ResponseEntity<?> response = paymentStatusControllers.getPaymentStatusNameById(1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sampleStatus, response.getBody());
        verify(paymentStatusService, times(1)).getPaymentStatusNameById(1);
    }

    @Test
    void getPaymentStatusNameById_WhenUnexpectedException_ShouldThrowHttpServerErrorException() {
        // Arrange
        when(paymentStatusService.getPaymentStatusNameById(1)).thenThrow(
                new RuntimeException("Unexpected null")
        );

        // Act & Assert
        HttpServerErrorException exception = assertThrows(HttpServerErrorException.class, () -> {
            paymentStatusControllers.getPaymentStatusNameById(1);
        });

        assertEquals("500 Unexpected error", exception.getMessage());
        verify(paymentStatusService, times(1)).getPaymentStatusNameById(1);
    }

    @Test
    void getPaymentStatusNameById_WhenHttpServerErrorExceptionThrown_ShouldWrapAndThrow() {
        when(paymentStatusService.getPaymentStatusNameById(1)).thenThrow(
                new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "DB error")
        );

        HttpServerErrorException exception = assertThrows(HttpServerErrorException.class, () -> {
            paymentStatusControllers.getPaymentStatusNameById(1);
        });

        assertEquals("500 Error retrieving payment status", exception.getMessage());
    }




}
