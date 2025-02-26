ALTER TABLE tourist_sheets
    ADD destination VARCHAR(50);

ALTER TABLE tourist_sheets
    ALTER COLUMN destination SET NOT NULL;

ALTER TABLE customers
    ALTER COLUMN phone TYPE VARCHAR(255) USING (phone::VARCHAR(255));

ALTER TABLE emergency_contacts
    ALTER COLUMN phone TYPE VARCHAR(255) USING (phone::VARCHAR(255));