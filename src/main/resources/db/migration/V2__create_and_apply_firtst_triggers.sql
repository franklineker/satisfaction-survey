CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_at = CURRENT_TIMESTAMP;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- app_user
CREATE TRIGGER set_updated_at_app_user
BEFORE UPDATE ON app_user
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

-- survey
CREATE TRIGGER set_updated_at_survey
BEFORE UPDATE ON survey
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

-- question
CREATE TRIGGER set_updated_at_question
BEFORE UPDATE ON question
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

-- recipient
CREATE TRIGGER set_updated_at_recipient
BEFORE UPDATE ON recipient
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

-- response
CREATE TRIGGER set_updated_at_response
BEFORE UPDATE ON response
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

-- oauth2_client
CREATE TRIGGER set_updated_at_oauth2_client
BEFORE UPDATE ON oauth2_client
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

