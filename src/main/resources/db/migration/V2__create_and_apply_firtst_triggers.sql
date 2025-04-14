CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE OR REPLACE FUNCTION set_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'INSERT') THEN
        NEW.created_at = CURRENT_TIMESTAMP;
        NEW.updated_at = CURRENT_TIMESTAMP;
    ELSIF (TG_OP = 'UPDATE') THEN
        NEW.updated_at = CURRENT_TIMESTAMP;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- app_user
CREATE OR REPLACE TRIGGER set_updated_at_app_user
BEFORE UPDATE ON app_user
FOR EACH ROW
EXECUTE FUNCTION set_timestamp();

-- survey
CREATE OR REPLACE TRIGGER set_updated_at_survey
BEFORE UPDATE ON survey
FOR EACH ROW
EXECUTE FUNCTION set_timestamp();

-- question
CREATE OR REPLACE TRIGGER set_updated_at_question
BEFORE UPDATE ON question
FOR EACH ROW
EXECUTE FUNCTION set_timestamp();

-- recipient
CREATE OR REPLACE TRIGGER set_updated_at_recipient
BEFORE UPDATE ON recipient
FOR EACH ROW
EXECUTE FUNCTION set_timestamp();

-- response
CREATE OR REPLACE TRIGGER set_updated_at_response
BEFORE UPDATE ON response
FOR EACH ROW
EXECUTE FUNCTION set_timestamp();

-- oauth2_client
CREATE OR REPLACE TRIGGER set_updated_at_oauth2_client
BEFORE UPDATE ON oauth2_client
FOR EACH ROW
EXECUTE FUNCTION set_timestamp();

-- role
CREATE OR REPLACE TRIGGER set_updated_at_role
BEFORE UPDATE ON role
FOR EACH ROW
EXECUTE FUNCTION set_timestamp();

