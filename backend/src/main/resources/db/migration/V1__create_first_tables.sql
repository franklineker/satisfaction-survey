CREATE TABLE app_user (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(255) NOT NULL,
    password VARCHAR(512),
    roles TEXT[] DEFAULT ARRAY['USER'],
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    expired BOOLEAN DEFAULT FALSE,
    locked BOOLEAN DEFAULT FALSE,
    credentials_expired BOOLEAN DEFAULT FALSE,
    disabled BOOLEAN DEFAULT FALSE,
    CONSTRAINT unique_username UNIQUE (username),
    CONSTRAINT unique_tax_number UNIQUE (tax_number)
);

CREATE TABLE person (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    tax_number VARCHAR(14),
    phone_number VARCHAR(25),
    CONSTRAINT unique_tax_number UNIQUE (tax_number)
);

CREATE TABLE role (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(20) NOT NULL CHECK (name IN ('ADMIN', 'USER'))
);

INSERT INTO role (name) VALUES
    ('ADMIN'),
    ('USER');

CREATE TABLE app_user_role (
    user_id UUID REFERENCES app_user(id) ON DELETE CASCADE,
    role_id UUID REFERENCES role(id) ON DELETE CASCADE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_app_user_role PRIMARY KEY(user_id, role_id)
);

CREATE TABLE survey (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    status VARCHAR(20) NOT NULL CHECK (status IN ('DRAFT', 'ACTIVE', 'CLOSED')),
    is_anonymous BOOLEAN DEFAULT FALSE,
    survey_type VARCHAR(50), -- customers can choose whatever they want.
    creator_id UUID NOT NULL REFERENCES app_user(id),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE question (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    survey_id UUID NOT NULL REFERENCES survey(id) ON DELETE CASCADE,
    text VARCHAR(1024) NOT NULL,
    type VARCHAR(50) NOT NULL CHECK (type IN (
        'SINGLE_CHOICE', 'MULTIPLE_CHOICE', 'YES_NO', 'RATING_SCALE', 'OPEN_TEXT'
    )),
    is_required BOOLEAN DEFAULT FALSE,
    display_order INTEGER,
    scale_min INTEGER,
    scale_max INTEGER,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE contact (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255),
    tax_number VARCHAR(14),
    phone_number VARCHAR(25) NOT NULL,
    deleted BOOLEAN DEFAULT FALSE,
    deleted_at TIMESTAMP WITH TIME ZONE,
    deleted_by UUID REFERENCES app_user(id),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE response (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    campaign_id UUID NOT NULL REFERENCES contact(id),
    survey_id UUID NOT NULL REFERENCES survey(id),
    question_id UUID NOT NULL REFERENCES question(id),
    contact_id UUID NOT NULL REFERENCES contact(id),
    answer TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE campaign (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(255) NOT NULL UNIQUE,
    survey_id UUID NOT NULL REFERENCES survey(id),
    attempts INTEGER NOT NULL DEFAULT 1, -- number of sending attempts
    max_responses INTEGER,
    sent_at TIMESTAMP WITH TIME ZONE,
    responded_at TIMESTAMP WITH TIME ZONE,
    status VARCHAR(20) NOT NULL DEFAULT 'CREATED' CHECK (status IN ('CREATED', 'SENT', 'RESPONDED', 'FAILED', 'CANCELED')),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE campaign_contact (
    campaign_id UUID NOT NULL REFERENCES campaign(id) ON DELETE CASCADE,
    contact_id UUID NOT NULL REFERENCES contact(id) ON DELETE CASCADE,
    PRIMARY KEY (campaign_id, contact_id)
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

