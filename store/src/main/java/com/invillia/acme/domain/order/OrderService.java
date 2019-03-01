package com.invillia.acme.domain.order;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.invillia.acme.domain.order.Order.OrderStatus;
import com.invillia.acme.domain.payment.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderService {

	private final OrderRepository orderRepository;
	private final PaymentService paymentService;

	public Order create(Order order) {
		if (order.getId() != null) {
			throw new RuntimeException("create.order.id.notNull");
		}

		order.setConfirmationDate(null);
		order.setStatus(OrderStatus.CREATED);
		order.getOrderItens().stream().forEach(orderItem -> orderItem.setOrder(order));
		return orderRepository.save(order);
	}

	public Order findById(UUID id) {
		return orderRepository.findById(id).orElse(null);
	}

	public Order findByIdWithStoreAndItens(UUID id) {
		return orderRepository.findByIdWithStoreAndItens(id);
	}

	public Page<Order> findAll(Pageable pageable) {
		return orderRepository.findAll(pageable);
	}

	public Page<Order> findAll(Specification<Order> spec, Pageable pageable) {
		return orderRepository.findAll(spec, pageable);
	}

	public Order payment(UUID id, String creditCardNumber) {
		Order order = findById(id);

		if (order.getStatus() != OrderStatus.CREATED) {
			throw new RuntimeException("order.payment.notAllowed");
		}

		order.setStatus(OrderStatus.PAYMENT_REQUESTED);
		orderRepository.save(order);

		paymentService.performPayment(id, creditCardNumber);

		return order;
	}

	public Order refund(UUID id) {
		Order order = findById(id);

		if (order.getStatus() != OrderStatus.CONFIRMED) {
			throw new RuntimeException("order.refund.notAllowed");
		}

		order.setStatus(OrderStatus.REFUND_REQUESTED);
		orderRepository.save(order);

		paymentService.performRefund(order);

		return order;
	}

}
