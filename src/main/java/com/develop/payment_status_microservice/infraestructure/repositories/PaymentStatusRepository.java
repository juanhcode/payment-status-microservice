package com.develop.payment_status_microservice.infraestructure.repositories;

import com.develop.payment_status_microservice.domain.models.PaymentStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentStatusRepository extends CrudRepository<PaymentStatus, Integer> {
    Optional<PaymentStatus> getPaymentStatusNameById(Integer paymentStatusId);
}