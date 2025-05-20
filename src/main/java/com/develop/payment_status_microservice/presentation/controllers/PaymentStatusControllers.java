package com.develop.payment_status_microservice.presentation.controllers;

import com.develop.payment_status_microservice.domain.interfaces.PaymentStatusService;
import com.develop.payment_status_microservice.domain.models.PaymentStatus;
import com.develop.payment_status_microservice.presentation.exceptions.ResourceNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Optional;

@RestController
@RequestMapping("/payment-status")
public class PaymentStatusControllers {
    @Lazy
    private final PaymentStatusService paymentStatusService;

    public PaymentStatusControllers(PaymentStatusService paymentStatusService) {
        this.paymentStatusService = paymentStatusService;
    }

    @GetMapping("/{paymentStatusId}")
    public ResponseEntity<?> getPaymentStatusNameById(@PathVariable Integer paymentStatusId) {
        try {
            Optional<PaymentStatus> paymentStatusOptional = paymentStatusService.getPaymentStatusNameById(paymentStatusId);
            if(paymentStatusOptional.isPresent()) {
                return ResponseEntity.ok(paymentStatusOptional.get());
            } else {
                throw new ResourceNotFoundException("Payment status with id: " + paymentStatusId + " not found");
            }
        } catch (HttpServerErrorException ex) {
            System.out.println("Error retrieving payment status, exception message: " + ex.getMessage());
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving payment status");
        } catch (Exception ex) {
            System.out.println("Unexpected error: " + ex.getMessage());
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error");
        }
    }
}
