ALTER TABLE product
ADD image text;

UPDATE product
SET image = '';

ALTER TABLE product
ALTER COLUMN image SET NOT NULL;