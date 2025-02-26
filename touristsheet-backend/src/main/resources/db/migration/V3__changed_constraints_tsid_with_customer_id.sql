ALTER TABLE tourist_sheets
    DROP CONSTRAINT uc_tourist_sheet_customer_id;

ALTER TABLE tourist_sheets
    ADD CONSTRAINT uc_tourist_sheet_customer_id UNIQUE (id, customer_id);