CREATE TABLE users
(
    id       INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR(10)  NOT NULL,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(20)  NOT NULL
);

CREATE TABLE orders
(
    id         INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    order_date TIMESTAMP NOT NULL,
    user_id    INT,
    FOREIGN KEY (user_id) REFERENCES users
);

CREATE TABLE items
(
    id    INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name  VARCHAR(30)      NOT NULL UNIQUE,
    price DOUBLE PRECISION NOT NULL CHECK (price >= 0 AND price <= 1000000000)
);

CREATE TABLE order_details
(
    id       INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    quantity INT NOT NULL CHECK (quantity >= 0 AND quantity <= 100),
    order_id INT,
    item_id  INT,
    FOREIGN KEY (order_id) REFERENCES orders,
    FOREIGN KEY (item_id) REFERENCES items
);

CREATE TABLE carts
(
    id      INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users
);

CREATE TABLE cart_details
(
    id         INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    quantity   INT       NOT NULL CHECK (quantity >= 0 AND quantity <= 100),
    added_date TIMESTAMP NOT NULL,
    item_id    INT,
    cart_id    INT,
    FOREIGN KEY (item_id) REFERENCES items,
    FOREIGN KEY (cart_id) REFERENCES carts
);



