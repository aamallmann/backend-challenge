package com.invillia.acme.domain.orderitem;

import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderItemService {

	private final OrderItemRepository orderItemRepository;

	public Collection<OrderItem> findByOrderId(UUID orderId) {
		return orderItemRepository.findByOrderId(orderId);
	}

	public Collection<OrderItem> findByIdAndOrderId(UUID id, UUID orderId) {
		return orderItemRepository.findByIdAndOrderId(id, orderId);
	}

}
