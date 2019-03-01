package com.invillia.acme.domain.orderitem;

import java.util.Collection;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {

	Collection<OrderItem> findByOrderId(UUID orderId);

	Collection<OrderItem> findByIdAndOrderId(UUID id, UUID orderId);

}
