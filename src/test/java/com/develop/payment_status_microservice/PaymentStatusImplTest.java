package com.develop.payment_status_microservice;
import com.develop.payment_status_microservice.application.use_cases.PaymentStatusImpl;
import com.develop.payment_status_microservice.domain.models.PaymentStatus;
import com.develop.payment_status_microservice.infraestructure.repositories.PaymentStatusRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentStatusImplTest {

    @Mock
    private PaymentStatusRepository paymentStatusRepository;

    @InjectMocks
    private PaymentStatusImpl paymentStatusImpl;

    @Test
    void testGetPaymentStatusNameById_WhenStatusExists() {
        // Arrange
        int id = 1;
        PaymentStatus expectedStatus = new PaymentStatus();
        expectedStatus.setId(id);
        expectedStatus.setName("PAID");

        when(paymentStatusRepository.getPaymentStatusNameById(id)).thenReturn(Optional.of(expectedStatus));

        // Act
        Optional<PaymentStatus> result = paymentStatusImpl.getPaymentStatusNameById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("PAID", result.get().getName());
        verify(paymentStatusRepository, times(1)).getPaymentStatusNameById(id);
    }

    @Test
    void testGetPaymentStatusNameById_WhenStatusDoesNotExist() {
        // Arrange
        int id = 999;

        when(paymentStatusRepository.getPaymentStatusNameById(id)).thenReturn(Optional.empty());

        // Act
        Optional<PaymentStatus> result = paymentStatusImpl.getPaymentStatusNameById(id);

        // Assert
        assertFalse(result.isPresent());
        verify(paymentStatusRepository, times(1)).getPaymentStatusNameById(id);
    }
}
