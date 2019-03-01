package com.invillia.acme.domain.store.spec;

import org.springframework.data.jpa.domain.Specification;

import com.invillia.acme.domain.store.Store;

import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@And({ @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
		@Spec(path = "address", params = "address", spec = LikeIgnoreCase.class) })
public interface StoreSpec extends Specification<Store> {

}
