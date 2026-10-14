-- Complete audit metadata for entities that inherit the shared BaseEntity.
ALTER TABLE product_price
    ADD COLUMN created_by VARCHAR(128),
    ADD COLUMN updated_by VARCHAR(128);

ALTER TABLE product_inventory
    ADD COLUMN created_by VARCHAR(128),
    ADD COLUMN updated_by VARCHAR(128);
