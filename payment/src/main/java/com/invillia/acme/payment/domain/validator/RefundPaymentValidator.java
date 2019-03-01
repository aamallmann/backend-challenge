package com.invillia.acme.payment.domain.validator;

import java.time.LocalDateTime;

import com.invillia.acme.payment.domain.Payment;
import com.invillia.acme.payment.domain.Payment.PaymentStatus;
import com.invillia.acme.payment.exception.PaymentException;

public class RefundPaymentValidator {

	public static void validate(Payment payment) {
		if (payment == null)
			throw new PaymentException("refund.null.payment");

		if (payment.getStatus() != PaymentStatus.CONFIRMED)
			throw new PaymentException("refund.payment.status.notAllowed");

		if (payment.getPaymentDate().plusDays(10).isBefore(LocalDateTime.now()))
			throw new PaymentException("refund.payment.expired.date");
	}

}
