-- Align the pricing currency column with the JPA VARCHAR(3) mapping.
-- Trim CHAR padding while preserving existing currency codes and defaults.
ALTER TABLE pricing
    ALTER COLUMN currency TYPE VARCHAR(3)
    USING BTRIM(currency);
