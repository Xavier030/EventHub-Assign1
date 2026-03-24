-- Table for event categories
CREATE TABLE categories (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(100) NOT NULL
);

-- Table for events
CREATE TABLE events (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        description TEXT,
                        price DECIMAL(10,2),
                        date DATETIME,
                        is_active BOOLEAN,
                        category_id INT,
                        CONSTRAINT fk_events_category FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Table for users
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       password VARCHAR(255) NOT NULL
);

-- Table for registrations (a user can have multiple registrations)
CREATE TABLE registrations (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               registration_date DATETIME NOT NULL,
                               user_id INT,
                               CONSTRAINT fk_registrations_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Table for registration items (tickets per registration)
CREATE TABLE registration_items (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    quantity INT NOT NULL,
                                    registration_id INT,
                                    event_id INT,
                                    CONSTRAINT fk_registration_items_registration FOREIGN KEY (registration_id) REFERENCES registrations(id),
                                    CONSTRAINT fk_registration_items_event FOREIGN KEY (event_id) REFERENCES events(id)
);

CREATE TABLE roles (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(50)
);

CREATE TABLE user_roles (
                            user_id BIGINT,
                            role_id BIGINT,
                            PRIMARY KEY (user_id, role_id)
);

CREATE TABLE password_reset_tokens (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       token VARCHAR(255),
                                       expiry_date DATETIME,
                                       user_id INT,
                                       CONSTRAINT fk_reset_user FOREIGN KEY (user_id) REFERENCES users(id)
);