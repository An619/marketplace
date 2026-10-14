-- Align currency columns with the VARCHAR(3) mapping used by JPA entities.
-- Trim CHAR padding while preserving existing currency codes and defaults.
ALTER TABLE product
    ALTER COLUMN currency TYPE VARCHAR(3)
    USING BTRIM(currency);

ALTER TABLE product_price
    ALTER COLUMN currency TYPE VARCHAR(3)
    USING BTRIM(currency);
