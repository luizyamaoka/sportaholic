ALTER TABLE uri
ADD COLUMN meta_description text NOT NULL CHECK(length(meta_description)<160) DEFAULT '';

ALTER TABLE uri
ALTER COLUMN meta_description DROP DEFAULT;