package com.develop.payment_status_microservice.application.use_cases;

import com.develop.payment_status_microservice.domain.interfaces.PaymentStatusService;
import com.develop.payment_status_microservice.domain.models.PaymentStatus;
import com.develop.payment_status_microservice.infraestructure.repositories.PaymentStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentStatusImpl implements PaymentStatusService {
    private final PaymentStatusRepository paymentStatusRepository;

    public PaymentStatusImpl(PaymentStatusRepository paymentStatusRepository) {
        this.paymentStatusRepository = paymentStatusRepository;
    }

    @Override
    public Optional<PaymentStatus> getPaymentStatusNameById(Integer paymentStatusId) {
        return paymentStatusRepository.getPaymentStatusNameById(paymentStatusId);
    }
}
