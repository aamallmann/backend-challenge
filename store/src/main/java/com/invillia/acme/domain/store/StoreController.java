package com.invillia.acme.domain.store;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.domain.store.spec.StoreSpec;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StoreController {

	private final StoreService storeService;

	@GetMapping("")
	public ResponseEntity<Page<Store>> getAll(Pageable pageable) {
		return ResponseEntity.ok(storeService.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Store> getById(@PathVariable UUID id) {
		return ResponseEntity.ok(storeService.findById(id));
	}

	@GetMapping("/search")
	public ResponseEntity<Page<Store>> search(StoreSpec storeSpec, Pageable pageable) {
		return ResponseEntity.ok(storeService.findAll(storeSpec, pageable));
	}

	@PostMapping("")
	public ResponseEntity<Store> create(@RequestBody Store store) {
		return ResponseEntity.ok(storeService.create(store));
	}

	@PutMapping("")
	public ResponseEntity<Store> update(@RequestBody Store store) {
		return ResponseEntity.ok(storeService.update(store));
	}

}
