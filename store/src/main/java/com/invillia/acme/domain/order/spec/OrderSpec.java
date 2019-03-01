package com.invillia.acme.domain.order.spec;

import org.springframework.data.jpa.domain.Specification;

import com.invillia.acme.domain.order.Order;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@And({ @Spec(path = "address", params = "address", spec = LikeIgnoreCase.class),
		@Spec(path = "confirmationDate", params = "confirmationDate", spec = Equal.class, config = "yyyy-MM-dd'T'HH:mm:ss.SSS"),
		@Spec(path = "status", params = "status", spec = Equal.class) })
public interface OrderSpec extends Specification<Order> {

}
