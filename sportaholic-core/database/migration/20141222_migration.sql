CREATE TABLE sport (
	id			serial		NOT NULL	PRIMARY KEY,
	created_at	timestamp	NOT NULL,
	updated_at	timestamp	NOT NULL,
	name		text		NOT NULL
);

CREATE TABLE client (
	id			serial		NOT NULL	PRIMARY KEY,
	created_at	timestamp	NOT NULL,
	updated_at	timestamp	NOT NULL,
	email		text		NOT NULL	UNIQUE,
	password	text		NOT NULL,
	cpf			text		CHECK(length(cpf)>=11)		CHECK(length(cpf)<=11),
	first_name	text		NOT NULL,
	last_name	text,
	birth_date	date,
	gender		text		CHECK(length(gender)>=1)	CHECK(length(gender)<=1)
);

CREATE TABLE author (
	id			serial		NOT NULL	PRIMARY KEY,
	created_at	timestamp	NOT NULL,
	updated_at	timestamp	NOT NULL,
	name		text		NOT NULL,
	description	text		
);

CREATE TABLE article (
	id			serial		NOT NULL	PRIMARY KEY,
	created_at	timestamp	NOT NULL,
	updated_at	timestamp	NOT NULL,
	title		text		NOT NULL,
	subtitle	text		NOT NULL,
	content		text		NOT NULL,
	author_id	integer		NOT NULL	REFERENCES author(id)
);

CREATE TABLE article_type (
	id			serial		NOT NULL	PRIMARY KEY,
	created_at	timestamp	NOT NULL,
	updated_at	timestamp	NOT NULL,
	name		text		NOT NULL	UNIQUE
);

CREATE TABLE interest (
	id			serial		NOT NULL	PRIMARY KEY,
	created_at	timestamp	NOT NULL,
	client_id	integer		NOT NULL	REFERENCES client(id)	ON DELETE CASCADE,
	sport_id	integer		NOT NULL	REFERENCES sport(id)	ON DELETE CASCADE
);

CREATE TABLE article_is_sport (
	id			serial		NOT NULL	PRIMARY KEY,
	created_at	timestamp	NOT NULL,
	article_id	integer		NOT NULL	REFERENCES article(id)	ON DELETE CASCADE,
	sport_id	integer		NOT NULL	REFERENCES sport(id)	ON DELETE CASCADE
);

CREATE TABLE reading (
	id			serial		NOT NULL	PRIMARY KEY,
	created_at	timestamp	NOT NULL,
	client_id	integer		NOT NULL	REFERENCES client(id)	ON DELETE CASCADE,
	article_id	integer		NOT NULL	REFERENCES article(id)	ON DELETE CASCADE
);

CREATE TABLE article_is_type (
	id			serial		NOT NULL	PRIMARY KEY,
	created_at	timestamp	NOT NULL,
	article_id	integer		NOT NULL	REFERENCES article(id)	ON DELETE CASCADE,
	article_type_id	integer	NOT NULL	REFERENCES article_type(id)	ON DELETE CASCADE
);

CREATE TABLE article_comment (
	id			serial		NOT NULL	PRIMARY KEY,
	created_at	timestamp	NOT NULL,
	updated_at	timestamp	NOT NULL,
	content		text		NOT NULL,
	article_id	integer		NOT NULL	REFERENCES article(id)	ON DELETE CASCADE,
	client_id	integer		NOT NULL	REFERENCES client(id)	ON DELETE CASCADE
);