CREATE TABLE user
(
    id       INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR(10) NOT NULL,
    password VARCHAR(10) NOT NULL,
    role     VARCHAR(15) NOT NULL,
);

CREATE TABLE order_details
(
    id       INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    quantity INT NOT NULL CHECK (quantity >= 0 AND quantity <= 100),
    FOREIGN KEY (order_id) REFERENCES order,
    FOREIGN KEY (item_id) REFERENCES item
);


CREATE TABLE order
(
    id         INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    order_date TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user
);

CREATE TABLE item
(
    id   INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    price DOUBLE NOT NULL CHECK (price >= 0 A   ND price <= 1000000000),
);

CREATE TABLE cart_details
(
    id         INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    quantity   INT       NOT NULL CHECK (quantity >= 0 AND quantity <= 100),
    added_date TIMESTAMP NOT NULL,
    FOREIGN KEY (item_id) REFERENCES item,
    FOREIGN KEY (cart_id) REFERENCES cart

);

CREATE TABLE cart
(
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    FOREIGN KEY (user_id) REFERENCES user
);



