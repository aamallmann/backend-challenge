package com.invillia.acme.domain.store;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Store {

	@Id
	@GeneratedValue
	private UUID id;

	@Size(min = 1, max = 100, message = "store.name.size")
	@NotNull(message = "store.name.notNull")
	private String name;

	@Size(min = 1, max = 200, message = "store.address.size")
	@NotNull(message = "store.address.notNull")
	private String address;

}
