CREATE TABLE car
(
    id                 UUID PRIMARY KEY,
    name               TEXT,
    category           TEXT,
    brand              TEXT,
    description        TEXT,
    manufacturing_date DATE,
    price              DECIMAL,
    created_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id            UUID,
    FOREIGN KEY (user_id) REFERENCES app_user (id)
);
