package com.invillia.acme.domain.payment;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

@FeignClient(name = "acme-payment")
public interface PaymentClient {

	@PostMapping("/payments")
	PaymentResponse payment(//
			@RequestParam("reference") String reference, //
			@RequestParam("referenceId") String referenceId, //
			@RequestParam("creditCardNumber") String creditCardNumber);

	@PostMapping("/payments/{id}/refund")
	PaymentResponse refund(@PathVariable("id") UUID id);

	@JsonIgnoreProperties(ignoreUnknown = true)
	public class PaymentResponse {
		@Getter
		private UUID id;
	}

}
