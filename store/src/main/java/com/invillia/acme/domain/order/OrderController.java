package com.invillia.acme.domain.order;

import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.domain.order.spec.OrderSpec;
import com.invillia.acme.domain.orderitem.OrderItem;
import com.invillia.acme.domain.orderitem.OrderItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

	private final OrderService orderService;
	private final OrderItemService orderItemService;

	@GetMapping("")
	public ResponseEntity<Page<Order>> getAll(Pageable pageable) {
		return ResponseEntity.ok(orderService.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Order> getById(@PathVariable UUID id) {
		return ResponseEntity.ok(orderService.findByIdWithStoreAndItens(id));
	}

	@GetMapping("/search")
	public ResponseEntity<Page<Order>> search(OrderSpec orderSpec, Pageable pageable) {
		return ResponseEntity.ok(orderService.findAll(orderSpec, pageable));
	}

	@PostMapping("")
	public ResponseEntity<Order> create(@RequestBody Order order) {
		return ResponseEntity.ok(orderService.create(order));
	}

	@PostMapping("/{id}/payment")
	public ResponseEntity<Order> confirm(@PathVariable UUID id,
			@RequestParam(name = "creditCardNumber") String creditCardNumber) {
		return ResponseEntity.ok(orderService.payment(id, creditCardNumber));
	}

	@PostMapping("/{id}/refund")
	public ResponseEntity<Order> refund(@PathVariable UUID id) {
		return ResponseEntity.ok(orderService.refund(id));
	}

	@GetMapping("/{id}/itens")
	public ResponseEntity<Collection<OrderItem>> getItensByOrder(@PathVariable UUID id) {
		return ResponseEntity.ok(orderItemService.findByOrderId(id));
	}

	@GetMapping("/{orderId}/itens/{itemId}")
	public ResponseEntity<Collection<OrderItem>> getItemByIdAndOrder(@PathVariable UUID orderId,
			@PathVariable UUID itemId) {
		return ResponseEntity.ok(orderItemService.findByIdAndOrderId(itemId, orderId));
	}

}
