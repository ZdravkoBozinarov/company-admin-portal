CREATE TABLE quests (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    subtitle VARCHAR(255),
    description TEXT,
    category VARCHAR(50),
    enabled BOOLEAN,
    hidden BOOLEAN,
    not_before TIMESTAMP,
    expiry TIMESTAMP,
    template VARCHAR(255),
    data TEXT
);
