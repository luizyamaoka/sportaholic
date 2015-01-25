CREATE TABLE uri (
	id				serial		NOT NULL	PRIMARY KEY,
	created_at		timestamp	NOT NULL,
	updated_at		timestamp	NOT NULL,
	uri				text		NOT NULL	UNIQUE,
	friendly_uri	text		NOT NULL	UNIQUE,
	name			text		NOT NULL,
	parent_id		integer		REFERENCES uri(id)
);