CREATE TABLE product (
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    name TEXT NOT NULL,
    price_in_cents INT NOT NULL
);