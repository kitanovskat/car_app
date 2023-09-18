CREATE TABLE car_ownership_history
(
    id        SERIAL PRIMARY KEY,
    car_id    UUID REFERENCES car(id),
    user_id   UUID REFERENCES app_user(id),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
