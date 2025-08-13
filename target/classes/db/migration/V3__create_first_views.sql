CREATE VIEW active_contact AS
SELECT
    id,
    first_name,
    last_name,
    email,
    phone_number,
    tax_number,
    created_at,
    updated_at
FROM contact
WHERE deleted = FALSE;
