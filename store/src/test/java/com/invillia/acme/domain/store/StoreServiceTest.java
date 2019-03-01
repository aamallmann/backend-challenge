package com.invillia.acme.domain.store;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreServiceTest {

	@Autowired
	private StoreService service;

	private Store getExampleStore() {
		return Store.builder()//
				.name("Invillia")//
				.address("Rua Padre Duarte, 151, Jardim Nova América – Araraquara - SP")//
				.build();
	}

	@Test
	public void testCreation() {
		Store store = service.create(getExampleStore());

		assertThat(store)//
				.hasNoNullFieldsOrProperties()//
				.extracting("name", "address")//
				.containsExactly("Invillia", "Rua Padre Duarte, 151, Jardim Nova América – Araraquara - SP");

	}

	@Test
	public void testUpdate() {
		Store store = service.create(getExampleStore());
		store.setName("updated name");
		store.setAddress("updated address");
		service.update(store);

		assertThat(service.findById(store.getId()))//
				.hasNoNullFieldsOrProperties()//
				.extracting("name", "address")//
				.containsExactly("updated name", "updated address");
	}

	@Test
	public void testCreationWithId() {
		Store store = getExampleStore();
		store.setId(UUID.randomUUID());
		assertThatThrownBy(() -> service.create(store)).hasStackTraceContaining("create.store.id.notNull");
	}

	@Test
	public void testUpdateWithoutId() {
		Store store = getExampleStore();
		store.setId(null);
		assertThatThrownBy(() -> service.update(store)).hasStackTraceContaining("update.store.id.null");
	}

	@Test
	public void testUpdateInexistent() {
		Store store = getExampleStore();
		store.setId(UUID.randomUUID());
		assertThatThrownBy(() -> service.update(store)).hasStackTraceContaining("update.store.notExists");
	}

	@Test
	public void testStoreNameNullValidation() {
		Store store = getExampleStore();
		store.setName(null);
		assertThatThrownBy(() -> service.create(store)).hasStackTraceContaining("store.name.notNull");
	}

	@Test
	public void testStoreNameMinSizeValidation() {
		Store store = getExampleStore();
		store.setName("");
		assertThatThrownBy(() -> service.create(store)).hasStackTraceContaining("store.name.size");
	}

	@Test
	public void testStoreNameMaxSizeValidation() {
		Store store = getExampleStore();
		store.setName(String.join("", Collections.nCopies(101, "a")));
		assertThatThrownBy(() -> service.create(store)).hasStackTraceContaining("store.name.size");
	}

	@Test
	public void testStoreAddressNullValidation() {
		Store store = getExampleStore();
		store.setAddress(null);
		assertThatThrownBy(() -> service.create(store)).hasStackTraceContaining("store.address.notNull");
	}

	@Test
	public void testStoreAddressMinSizeValidation() {
		Store store = getExampleStore();
		store.setAddress("");
		assertThatThrownBy(() -> service.create(store)).hasStackTraceContaining("store.address.size");
	}

	@Test
	public void testStoreAddressMaxSizeValidation() {
		Store store = getExampleStore();
		store.setAddress(String.join("", Collections.nCopies(201, "a")));
		assertThatThrownBy(() -> service.create(store)).hasStackTraceContaining("store.address.size");
	}

}
