-- =========================
-- CLEAN DATA
-- =========================
DELETE FROM registration_items;
DELETE FROM registrations;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM roles;
DELETE FROM events;
DELETE FROM categories;

-- =========================
-- ROLES
-- =========================
INSERT INTO roles (name) VALUES
                             ('ROLE_USER'),
                             ('ROLE_ADMIN');

-- =========================
-- USERS (BCrypt for "123456")
-- =========================
INSERT INTO users (username, email, password) VALUES
                                                  ('admin', 'admin@example.com', '$2a$10$7QJQZqv5o0o6qv9Zxw4cQe9Qm3Jx7W8Qm3aQe1fZp6X9y0Q1mGk2e'),
                                                  ('alice', 'alice@example.com', '$2a$10$7QJQZqv5o0o6qv9Zxw4cQe9Qm3Jx7W8Qm3aQe1fZp6X9y0Q1mGk2e'),
                                                  ('bob', 'bob@example.com', '$2a$10$7QJQZqv5o0o6qv9Zxw4cQe9Qm3Jx7W8Qm3aQe1fZp6X9y0Q1mGk2e');

-- =========================
-- USER ROLES
-- =========================
INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u
         JOIN roles r ON r.name = 'ROLE_ADMIN'
WHERE u.username = 'admin';

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u
         JOIN roles r ON r.name = 'ROLE_USER'
WHERE u.username IN ('alice','bob');

-- =========================
-- CATEGORIES
-- =========================
INSERT INTO categories (name) VALUES
                                  ('Music'),
                                  ('Tech'),
                                  ('Sports');

-- =========================
-- EVENTS
-- =========================
INSERT INTO events (name, description, price, date, is_active, category_id) VALUES
                                                                                ('Rock Concert', 'An amazing rock concert.', 50.00, '2026-04-01 19:00:00', TRUE, 1),
                                                                                ('Tech Conference', 'Annual technology conference.', 200.00, '2026-05-15 09:00:00', TRUE, 2),
                                                                                ('Marathon', 'City marathon event.', 30.00, '2026-06-20 07:00:00', TRUE, 3);

-- =========================
-- REGISTRATIONS
-- =========================
INSERT INTO registrations (registration_date, user_id)
SELECT NOW(), id FROM users WHERE username = 'alice';

INSERT INTO registrations (registration_date, user_id)
SELECT NOW(), id FROM users WHERE username = 'bob';

-- =========================
-- REGISTRATION ITEMS
-- =========================
INSERT INTO registration_items (quantity, registration_id, event_id)
SELECT 2, r.id, 1
FROM registrations r
         JOIN users u ON r.user_id = u.id
WHERE u.username = 'alice'
    LIMIT 1;

INSERT INTO registration_items (quantity, registration_id, event_id)
SELECT 1, r.id, 2
FROM registrations r
         JOIN users u ON r.user_id = u.id
WHERE u.username = 'bob'
    LIMIT 1;