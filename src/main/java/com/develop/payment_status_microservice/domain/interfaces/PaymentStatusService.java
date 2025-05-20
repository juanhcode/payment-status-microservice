package com.develop.payment_status_microservice.domain.interfaces;

import com.develop.payment_status_microservice.domain.models.PaymentStatus;

import java.util.Optional;

public interface PaymentStatusService {
    Optional<PaymentStatus> getPaymentStatusNameById(Integer paymentStatusId);
}
