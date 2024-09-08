CREATE TABLE product
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    description TEXT,
    price       NUMERIC(10, 2) NOT NULL
);

INSERT INTO product (name, description, price) VALUES ('Product 1', 'Description 1', 10.00);
INSERT INTO product (name, description, price) VALUES ('Product 2', 'Description 2', 20.00);
INSERT INTO product (name, description, price) VALUES ('Product 3', 'Description 3', 30.00);

