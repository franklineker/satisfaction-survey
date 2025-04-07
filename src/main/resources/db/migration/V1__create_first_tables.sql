CREATE TABLE app_user (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(255) NOT NULL,
    password VARCHAR(512) NOT NULL,
    roles TEXT[] NOT NULL DEFAULT ARRAY['USER']::TEXT[],
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    tax_number VARCHAR(14) NOT NULL,
    phone_number VARCHAR(25),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    expired BOOLEAN DEFAULT FALSE,
    locked BOOLEAN DEFAULT FALSE,
    credentials_expired BOOLEAN DEFAULT FALSE,
    disabled BOOLEAN DEFAULT FALSE,
    CONSTRAINT unique_username UNIQUE (username),
    CONSTRAINT unique_tax_number UNIQUE (tax_number)
);

CREATE TABLE survey (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(20) NOT NULL CHECK (status IN ('DRAFT', 'ACTIVE', 'CLOSED')),
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    is_anonymous BOOLEAN DEFAULT TRUE,
    survey_type VARCHAR(50),
    max_responses INTEGER,
    creator_id UUID NOT NULL REFERENCES app_user(id),
    sent_at TIMESTAMP WITH TIME ZONE,
    responded_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE question (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    survey_id UUID NOT NULL REFERENCES survey(id),
    text VARCHAR(1024) NOT NULL,
    question_type VARCHAR(50) NOT NULL CHECK (question_type IN (
        'SINGLE_CHOICE', 'MULTIPLE_CHOICE', 'YES_NO', 'RATING_SCALE', 'OPEN_TEXT'
    )),
    is_required BOOLEAN DEFAULT FALSE,
    display_order INTEGER,
    scale_min INTEGER,
    scale_max INTEGER,
    sent_at TIMESTAMP WITH TIME ZONE,
    responded_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE recipient (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    survey_id UUID NOT NULL REFERENCES survey(id),
    name VARCHAR(255),
    phone_number VARCHAR(25) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE response (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    survey_id UUID NOT NULL REFERENCES survey(id),
    question_id UUID NOT NULL REFERENCES question(id),
    recipient_id UUID NOT NULL REFERENCES recipient(id),
    answer TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE oauth2_client (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    client_id VARCHAR(255) NOT NULL,
    client_secret VARCHAR(255) NOT NULL,
    authorization_grant_types TEXT[] NOT NULL,
    authentication_methods TEXT[] NOT NULL,
    redirect_uris TEXT[] NOT NULL,
    scopes TEXT[] NOT NULL,
    required_proof_key BOOLEAN DEFAULT TRUE,
    client_id_issued_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT unique_client_id UNIQUE (client_id)
);

