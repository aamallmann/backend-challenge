package com.invillia.acme.domain.orderitem;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.invillia.acme.domain.order.Order;

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
public class OrderItem {

	@Id
	@GeneratedValue
	private UUID id;

	@Size(min = 1, max = 100, message = "orderItem.description.size")
	@NotNull(message = "orderItem.description.notNull")
	private String description;

	@NotNull(message = "orderItem.unitPrice.notNull")
	@Min(value = 0, message = "orderItem.unitPrice.min")
	private BigDecimal unitPrice;

	@NotNull(message = "orderItem.quantity.notNull")
	@Min(value = 0, message = "orderItem.quantity.min")
	private BigDecimal quantity;

	@NotNull(message = "orderItem.order.notNull")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonIgnore
	private Order order;

}
