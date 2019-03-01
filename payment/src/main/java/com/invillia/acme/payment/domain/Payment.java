package com.invillia.acme.payment.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment {

	@Id
	@GeneratedValue
	private UUID id;

	@NotNull(message = "payment.status.notNull")
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;

	@JsonIgnore
	@Size(min = 16, max = 16, message = "payment.creditCardNumber.size")
	@NotNull(message = "payment.creditCardNumber.notNull")
	private String creditCardNumber;

	@NotNull(message = "payment.paymentDate.notNull")
	private LocalDateTime paymentDate;

	@Size(min = 1, max = 200, message = "payment.reference.size")
	@NotNull(message = "payment.reference.notNull")
	private String reference;

	@Size(min = 1, max = 200, message = "payment.referenceId.size")
	@NotNull(message = "payment.referenceId.notNull")
	private String referenceId;

	public enum PaymentStatus {
		CONFIRMED, REFUNDED;
	}

}
