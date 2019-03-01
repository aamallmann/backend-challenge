package com.invillia.acme.domain.order;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.invillia.acme.domain.orderitem.OrderItem;
import com.invillia.acme.domain.store.Store;

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
public class Order {

	@Id
	@GeneratedValue
	private UUID id;

	@Size(min = 1, max = 200, message = "order.address.size")
	@NotNull(message = "order.address.notNull")
	private String address;

	private LocalDateTime confirmationDate;

	@NotNull(message = "order.status.notNull")
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@NotNull(message = "order.store.notNull")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Store store;

	private UUID paymentId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
	private Collection<OrderItem> orderItens;

	public enum OrderStatus {
		CREATED, PAYMENT_REQUESTED, CONFIRMED, REFUND_REQUESTED, REFUNDED;
	}

}
