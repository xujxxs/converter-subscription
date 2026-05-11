CREATE TABLE subscription (
    username VARCHAR(255) PRIMARY KEY,
    type VARCHAR(30) NOT NULL,
    expires TIMESTAMP NOT NULL
);