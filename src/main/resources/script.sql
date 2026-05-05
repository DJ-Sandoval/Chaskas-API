CREATE TABLE roles (
                       id          INT AUTO_INCREMENT PRIMARY KEY,
                       name        VARCHAR(50) NOT NULL UNIQUE,        -- Admin, Cashier, Heladero, Customer
                       description TEXT,
                       created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE users (
                       id              INT AUTO_INCREMENT PRIMARY KEY,
                       role_id         INT NOT NULL,
                       username        VARCHAR(50) UNIQUE,
                       email           VARCHAR(100) UNIQUE NOT NULL,
                       password_hash   VARCHAR(255) NOT NULL,
                       full_name       VARCHAR(150),
                       phone           VARCHAR(20),
                       active          BOOLEAN DEFAULT TRUE,
                       last_login      TIMESTAMP NULL,
                       created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                       FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE RESTRICT
);

