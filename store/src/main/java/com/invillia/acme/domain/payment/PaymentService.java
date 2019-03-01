package com.invillia.acme.domain.payment;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.invillia.acme.domain.order.Order;
import com.invillia.acme.domain.order.Order.OrderStatus;
import com.invillia.acme.domain.order.OrderRepository;
import com.invillia.acme.domain.payment.PaymentClient.PaymentResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentService {

	private final PaymentClient paymentClient;
	private final OrderRepository orderRepository;

	@Async
	public void performPayment(UUID orderId, String creditCardNumber) {
		PaymentResponse payment = paymentClient.payment(//
				Order.class.getSimpleName(), //
				orderId.toString(), //
				creditCardNumber);

		if (payment != null) {
			confirmOrder(orderId, payment.getId());
		}
	}

	public Order confirmOrder(UUID id, UUID paymentId) {
		Order order = orderRepository.findById(id).orElse(null);
		order.setConfirmationDate(LocalDateTime.now());
		order.setStatus(OrderStatus.CONFIRMED);
		order.setPaymentId(paymentId);
		return orderRepository.save(order);
	}

	@Async
	public void performRefund(Order order) {
		PaymentResponse payment = paymentClient.refund(order.getPaymentId());

		if (payment != null) {
			confirmRefund(order.getId());
		}
	}

	public Order confirmRefund(UUID id) {
		Order order = orderRepository.findById(id).orElse(null);
		order.setStatus(OrderStatus.REFUNDED);
		return orderRepository.save(order);
	}

}
