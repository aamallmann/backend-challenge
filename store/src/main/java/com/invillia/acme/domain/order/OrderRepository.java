package com.invillia.acme.domain.order;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, UUID>, JpaSpecificationExecutor<Order> {

	@Query("select o from Order o left join fetch o.store left join fetch o.orderItens where o.id = :id")
	Order findByIdWithStoreAndItens(UUID id);

}
