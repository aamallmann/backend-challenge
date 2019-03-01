package com.invillia.acme.payment.exception;

public class PaymentException extends RuntimeException {

	private static final long serialVersionUID = 2890478691319755854L;

	public PaymentException(String message) {
		super(message);
	}

}
