-- Keep PostgreSQL timestamp columns aligned with Java Instant mappings.
-- Existing naive timestamps are interpreted as UTC during conversion.
ALTER TABLE mobile_user
    ALTER COLUMN pin_set_at TYPE TIMESTAMPTZ USING pin_set_at AT TIME ZONE 'UTC',
    ALTER COLUMN locked_until TYPE TIMESTAMPTZ USING locked_until AT TIME ZONE 'UTC',
    ALTER COLUMN created_at TYPE TIMESTAMPTZ USING created_at AT TIME ZONE 'UTC',
    ALTER COLUMN updated_at TYPE TIMESTAMPTZ USING updated_at AT TIME ZONE 'UTC';

ALTER TABLE user_profile
    ALTER COLUMN created_at TYPE TIMESTAMPTZ USING created_at AT TIME ZONE 'UTC',
    ALTER COLUMN updated_at TYPE TIMESTAMPTZ USING updated_at AT TIME ZONE 'UTC';
