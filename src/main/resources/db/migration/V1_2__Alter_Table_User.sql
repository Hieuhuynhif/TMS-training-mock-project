ALTER TABLE users
ALTER COLUMN username TYPE VARCHAR(50);

ALTER TABLE users
    ALTER COLUMN username SET NOT NULL;

-- Add the new 'provider' column
ALTER TABLE users
    ADD COLUMN provider VARCHAR(20);