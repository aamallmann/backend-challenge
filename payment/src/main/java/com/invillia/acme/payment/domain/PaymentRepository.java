package com.invillia.acme.payment.domain;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invillia.acme.payment.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {

}
