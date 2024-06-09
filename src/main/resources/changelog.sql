-- liquibase formatted sql

-- changeset liquibase:1
CREATE TABLE user (
    id BIGINT primary key AUTO_INCREMENT,
    email VARCHAR(255),
    name VARCHAR(255),
    password VARCHAR(255),
    status VARCHAR(255) -- Type of user
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

INSERT INTO user(email, name, password, status) VALUES('love@gmail.com', 'GoblinSecretary', 'enable', 'goblin');

-- changeset liquibase:2
ALTER TABLE account
    DROP total_balance,
    DROP knut_balance,
    DROP sickle_balance,
    DROP galleon_balance;

ALTER TABLE transaction
    DROP amount;

ALTER TABLE account
    ADD total_balance DECIMAL(10,2),
    ADD knut_balance DECIMAL(10,2),
    ADD sickle_balance DECIMAL(10,2),
    ADD galleon_balance DECIMAL(10,2);

ALTER TABLE account
    ADD amount DECIMAL(10,2);

-- changeset liquibase:3
ALTER TABLE account DROP amount;
ALTER TABLE transaction ADD amount DECIMAL(10,2);

-- changeset liquibase:4
ALTER TABLE transaction DROP dateTime;
ALTER TABLE transaction ADD date_time DATETIME;

-- changeset liquibase:5
ALTER TABLE user ADD phone_number VARCHAR(20);

-- changeset liquibase:6
ALTER TABLE transaction ADD source_currency VARCHAR(30);
ALTER TABLE transaction ADD destination_currency VARCHAR(30);

-- changeset liquibase:7
UPDATE user SET password = '9905c33933602ae804e94f7731ecb5faaf4d6770' WHERE id = 1;

-- changeset liquibase:8
CREATE TABLE user_account (
    user_id BIGINT NOT NULL,
    account_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, account_id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (account_id) REFERENCES account(id)
);
