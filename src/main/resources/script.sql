CREATE TABLE distances (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           ville1 VARCHAR(255),
                           ville2 VARCHAR(255),
                           distance_km INT,
                           time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
