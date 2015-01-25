CREATE TABLE purchase (
	id			serial		NOT NULL	PRIMARY KEY,
	created_at	timestamp	NOT NULL,
	updated_at	timestamp	NOT NULL,
	client_id	integer					REFERENCES client(id),
	is_closed	boolean		NOT NULL
);

CREATE TABLE brand (
	id			serial		NOT NULL	PRIMARY KEY,
	created_at	timestamp	NOT NULL,
	updated_at	timestamp	NOT NULL,
	name		text		NOT NULL,
	description	text		NOT NULL,
	logo		text		NOT NULL
);

CREATE TABLE product (
	id			serial		NOT NULL	PRIMARY KEY,
	created_at	timestamp	NOT NULL,
	updated_at	timestamp	NOT NULL,
	name		text		NOT NULL,
	description	text		NOT NULL,
	price		numeric(10, 2)	NOT NULL,
	is_active	boolean		NOT NULL,
	in_stock	integer		NOT NULL,
	brand_id	integer		NOT NULL	REFERENCES brand(id)	ON DELETE CASCADE
);

CREATE TABLE product_is_sport (
	id			serial		NOT NULL	PRIMARY KEY,
	created_at	timestamp	NOT NULL,
	product_id	integer		NOT NULL	REFERENCES product(id)	ON DELETE CASCADE,
	sport_id	integer		NOT NULL	REFERENCES sport(id)	ON DELETE CASCADE
);

CREATE TABLE product_view (
	id			serial		NOT NULL	PRIMARY KEY,
	created_at	timestamp	NOT NULL,
	product_id	integer		NOT NULL	REFERENCES product(id)	ON DELETE CASCADE,
	client_id	integer		NOT NULL	REFERENCES client(id)	ON DELETE CASCADE
);

CREATE TABLE status (
	id			serial		NOT NULL	PRIMARY KEY,
	created_at	timestamp	NOT NULL,
	updated_at	timestamp	NOT NULL,
	name		text		NOT NULL
);

CREATE TABLE purchase_line (
	id			serial		NOT NULL	PRIMARY KEY,
	created_at	timestamp	NOT NULL,
	updated_at	timestamp	NOT NULL,
	purchase_id	integer		NOT NULL	REFERENCES purchase(id)	ON DELETE CASCADE,
	product_id	integer		NOT NULL	REFERENCES product(id),
	quantity	integer		NOT NULL,
	price		numeric(10,2)	NOT NULL,
	status_id	integer		NOT NULL	REFERENCES status(id)
);

CREATE TABLE address (
	id			serial		NOT NULL	PRIMARY KEY,
	created_at	timestamp	NOT NULL,
	updated_at	timestamp	NOT NULL,
	client_id	integer		NOT NULL	REFERENCES client(id)	ON DELETE CASCADE,
	address		text		NOT NULL,
	number		text		NOT NULL,
	complement	text,
	cep			text		NOT NULL 	CHECK(length(cep)>=8)		CHECK(length(cep)<=8),
	city		text		NOT NULL,
	state		text		NOT NULL	CHECK(length(state)>=2)		CHECK(length(state)<=2)
);

CREATE TABLE shipping_address (
	id			serial		NOT NULL	PRIMARY KEY,
	created_at	timestamp	NOT NULL,
	updated_at	timestamp	NOT NULL,
	purchase_id	integer		NOT NULL	UNIQUE 	REFERENCES purchase(id) 	ON DELETE CASCADE,
	address		text		NOT NULL,
	number		text		NOT NULL,
	complement	text,
	cep			text		NOT NULL 	CHECK(length(cep)>=8)		CHECK(length(cep)<=8),
	city		text		NOT NULL,
	state		text		NOT NULL	CHECK(length(state)>=2)		CHECK(length(state)<=2)
);

CREATE TABLE product_category (
	id			serial		NOT NULL	PRIMARY KEY,
	created_at	timestamp	NOT NULL,
	updated_at	timestamp	NOT NULL,
	name		text		NOT NULL,
	sport_id	integer		NOT NULL	REFERENCES sport(id)	ON DELETE CASCADE
);

CREATE TABLE product_type (
	id					serial		NOT NULL	PRIMARY KEY,
	created_at			timestamp	NOT NULL,
	updated_at			timestamp	NOT NULL,
	name				text		NOT NULL,
	product_category_id	integer		NOT NULL	REFERENCES product_category(id)	ON DELETE CASCADE
);

CREATE TABLE product_is_type (
	id				serial		NOT NULL	PRIMARY KEY,
	created_at		timestamp	NOT NULL,
	product_id		integer		NOT NULL	REFERENCES product(id)	ON DELETE CASCADE,
	product_type_id	integer		NOT NULL	REFERENCES product_type(id)	ON DELETE CASCADE
);

CREATE TABLE product_comment (
	id			serial		NOT NULL	PRIMARY KEY,
	created_at	timestamp	NOT NULL,
	updated_at	timestamp	NOT NULL,
	content		text		NOT NULL,
	grade		integer		NOT NULL	CHECK(grade>=0)		CHECK(grade<=100),
	product_id	integer		NOT NULL	REFERENCES product(id)	ON DELETE CASCADE,
	client_id	integer		NOT NULL	REFERENCES client(id)	ON DELETE CASCADE
);

