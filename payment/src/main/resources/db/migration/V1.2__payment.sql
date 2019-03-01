CREATE TABLE payment (
	id UUID NOT NULL DEFAULT uuid_generate_v4(),
	status VARCHAR(100) NOT NULL,
	credit_card_number VARCHAR(16) NOT NULL,
	payment_date TIMESTAMP NOT NULL,
	reference VARCHAR(200) NOT NULL,
	reference_id VARCHAR(200) NOT NULL,
	CONSTRAINT payment_pk PRIMARY KEY (id)
);