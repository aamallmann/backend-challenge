CREATE TABLE order_item (
	id UUID NOT NULL DEFAULT uuid_generate_v4(),
	description VARCHAR(100) NOT NULL,
	unit_price NUMERIC(10, 2) NOT NULL,
	quantity NUMERIC(10, 2) NOT NULL,
	order_id UUID NOT NULL,
	CONSTRAINT order_item_pk PRIMARY KEY (id)
);

ALTER TABLE order_item ADD CONSTRAINT order_item_fk_order FOREIGN KEY (order_id) REFERENCES "order" (id);