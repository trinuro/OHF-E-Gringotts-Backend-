-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE user (
    id BIGINT primary key AUTO_INCREMENT,
    email VARCHAR(255),
    name VARCHAR(255),
    password VARCHAR(255),
    status boolean, -- Whether the user is banned or not
    type VARCHAR(255) -- Type of user
);

CREATE TABLE account (
    id BIGINT primary key AUTO_INCREMENT,
    total_balance BIGINT,
    user_id BIGINT,
    FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE SET NULL,
    knut_balance BIGINT,
    sickle_balance BIGINT,
    galleon_balance BIGINT
);

CREATE TABLE transaction(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    amount BIGINT,
    dateTime DATETIME,
    source_account_id BIGINT,
    destination_account_id BIGINT,
    FOREIGN KEY(source_account_id) REFERENCES account(id) ON DELETE SET NULL,
    FOREIGN KEY(destination_account_id) REFERENCES account(id) ON DELETE SET NULL,
    category VARCHAR(255),
    description VARCHAR(5000)
);

INSERT INTO user(email, name, password, status, type) VALUES('email@gmail.com', 'Goblin', 'enable', true, 'admin');