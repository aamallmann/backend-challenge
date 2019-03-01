package com.invillia.acme.domain.order;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.invillia.acme.domain.order.Order.OrderStatus;
import com.invillia.acme.domain.payment.PaymentService;
import com.invillia.acme.domain.store.Store;
import com.invillia.acme.domain.store.StoreService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private StoreService storeService;
	private Store store;

	@Before
	public void setup() {
		store = storeService.create(Store.builder().name("name").address("address").build());
	}

	private Order getExampleOrder() {
		return Order.builder()//
				.address("address")//
				.store(store)//
				.orderItens(Collections.emptyList())//
				.build();
	}

	@Test
	public void testCreation() {
		Order order = orderService.create(getExampleOrder());
		assertThat(orderService.findById(order.getId()))//
				.extracting("address", "confirmationDate", "status", "paymentId")//
				.containsExactly("address", null, OrderStatus.CREATED, null);
	}

	@Test
	public void testPaymentRequest() {
		Order order = orderService.create(getExampleOrder());
		orderService.payment(order.getId(), "1234567890123456");
		assertThat(orderService.findById(order.getId()))//
				.extracting("address", "confirmationDate", "status", "paymentId")//
				.containsExactly("address", null, OrderStatus.PAYMENT_REQUESTED, null);
	}

	@Test
	public void testRefoundRequest() {
		Order order = orderService.create(getExampleOrder());
		orderService.payment(order.getId(), "1234567890123456");

		// force confirm
		paymentService.confirmOrder(order.getId(), UUID.randomUUID());

		Order refoundOrder = orderService.refund(order.getId());

		assertThat(orderService.findById(order.getId()))//
				.extracting("address", "confirmationDate", "status", "paymentId")//
				.containsExactly("address", refoundOrder.getConfirmationDate(), OrderStatus.REFUND_REQUESTED,
						refoundOrder.getPaymentId());
	}

}
