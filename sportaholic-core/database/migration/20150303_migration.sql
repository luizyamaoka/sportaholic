ALTER TABLE product
ADD image timestamp;

UPDATE product
SET image = '';

ALTER TABLE product
ALTER COLUMN image SET NOT NULL;