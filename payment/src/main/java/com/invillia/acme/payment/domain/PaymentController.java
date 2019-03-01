package com.invillia.acme.payment.domain;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentController {

	private final PaymentService paymentService;

	@GetMapping("/{id}")
	public ResponseEntity<Payment> getById(@PathVariable UUID id) {
		return ResponseEntity.ok(paymentService.findById(id));
	}

	@PostMapping("")
	public ResponseEntity<Payment> requestPayment(@RequestParam("reference") String reference, //
			@RequestParam("referenceId") String referenceId, //
			@RequestParam("creditCardNumber") String creditCardNumber) {
		return ResponseEntity.ok(paymentService.createPayment(reference, referenceId, creditCardNumber));
	}

	@PostMapping("/{id}/refund")
	public ResponseEntity<Payment> requestRefund(@PathVariable UUID id) {
		return ResponseEntity.ok(paymentService.refundPayment(id));
	}

}
