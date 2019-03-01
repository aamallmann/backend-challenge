CREATE TABLE store (
	id UUID NOT NULL DEFAULT uuid_generate_v4(),
	name VARCHAR(100) NOT NULL,
	address VARCHAR(200) NOT NULL,
	CONSTRAINT store_pk PRIMARY KEY (id)
);