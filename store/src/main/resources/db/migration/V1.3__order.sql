CREATE TABLE "order" (
	id UUID NOT NULL DEFAULT uuid_generate_v4(),
	address VARCHAR(200) NOT NULL,
	confirmation_date TIMESTAMP,
	status VARCHAR(100) NOT NULL,
	store_id UUID NOT NULL,
	payment_id UUID,
	CONSTRAINT order_pk PRIMARY KEY (id)
);

ALTER TABLE "order" ADD CONSTRAINT order_fk_store FOREIGN KEY (store_id) REFERENCES store (id);