CREATE TABLE user_subscription (
    username VARCHAR(255) PRIMARY KEY,
    subscription_id BIGINT NOT NULL,
    expires TIMESTAMP NOT NULL,
    CONSTRAINT fk_user_subscription_subscription
        FOREIGN KEY (subscription_id)
        REFERENCES subscription(id)
        ON DELETE CASCADE
);