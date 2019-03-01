package com.invillia.acme.payment.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invillia.acme.payment.domain.Payment.PaymentStatus;
import com.invillia.acme.payment.domain.validator.RefundPaymentValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentService {

	private final PaymentRepository paymentRepository;

	public Payment findById(UUID id) {
		return paymentRepository.findById(id).orElse(null);
	}

	public Payment createPayment(String reference, String referenceId, String creditCardNumber) {
		Payment payment = Payment.builder()//
				.status(PaymentStatus.CONFIRMED)//
				.creditCardNumber(creditCardNumber)//
				.paymentDate(LocalDateTime.now())//
				.reference(reference)//
				.referenceId(referenceId)//
				.build();

		return paymentRepository.save(payment);
	}

	public Payment refundPayment(UUID id) {
		Payment payment = findById(id);
		RefundPaymentValidator.validate(payment);

		payment.setStatus(PaymentStatus.REFUNDED);

		return paymentRepository.save(payment);
	}

}
