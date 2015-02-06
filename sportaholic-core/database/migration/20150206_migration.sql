ALTER TABLE article
ADD published_at timestamp;

UPDATE article
SET published_at = created_at;

ALTER TABLE article
ALTER COLUMN published_at SET NOT NULL;