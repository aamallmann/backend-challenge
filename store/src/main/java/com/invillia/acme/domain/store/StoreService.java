package com.invillia.acme.domain.store;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StoreService {

	private final StoreRepository storeRepository;

	public Store create(Store store) {
		if (store.getId() != null) {
			throw new RuntimeException("create.store.id.notNull");
		}
		return storeRepository.save(store);
	}

	public Store update(Store store) {
		if (store.getId() == null) {
			throw new RuntimeException("update.store.id.null");
		}
		if (findById(store.getId()) == null) {
			throw new RuntimeException("update.store.notExists");
		}
		return storeRepository.save(store);
	}

	public Store findById(UUID id) {
		return storeRepository.findById(id).orElse(null);
	}

	public Page<Store> findAll(Pageable pageable) {
		return storeRepository.findAll(pageable);
	}

	public Page<Store> findAll(Specification<Store> spec, Pageable pageable) {
		return storeRepository.findAll(spec, pageable);
	}

}
