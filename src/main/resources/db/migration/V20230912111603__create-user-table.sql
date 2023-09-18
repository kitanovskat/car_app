CREATE TABLE app_user
(
    id         UUID PRIMARY KEY,
    first_name TEXT,
    last_name  TEXT,
    email      TEXT,
    money      DECIMAL,
    role       TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
