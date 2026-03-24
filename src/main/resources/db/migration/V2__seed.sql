DELETE FROM registration_items;
DELETE FROM registrations;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM roles;
DELETE FROM events;
DELETE FROM categories;


INSERT INTO roles (name) VALUES
                             ('ROLE_USER'),
                             ('ROLE_ADMIN');

-- admin -> 123456
-- alice -> 123456
-- bob   -> 123456
INSERT INTO users (username, email, password) VALUES
                                                  ('admin', 'admin@example.com', '$2a$10$Dow1rQYxV7G0d0Yk3y5C6eWJcX7j5s5z9k8y7F3bQ6L5x1e2h3i4a'),
                                                  ('alice', 'alice@example.com', '$2a$10$Dow1rQYxV7G0d0Yk3y5C6eWJcX7j5s5z9k8y7F3bQ6L5x1e2h3i4a'),
                                                  ('bob', 'bob@example.com', '$2a$10$Dow1rQYxV7G0d0Yk3y5C6eWJcX7j5s5z9k8y7F3bQ6L5x1e2h3i4a');



INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r WHERE u.username='admin' AND r.name='ROLE_ADMIN';

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r WHERE u.username='alice' AND r.name='ROLE_USER';

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r WHERE u.username='bob' AND r.name='ROLE_USER';


INSERT INTO categories (name) VALUES
                                  ('Music'),
                                  ('Tech'),
                                  ('Sports');


INSERT INTO events (name, description, price, date, is_active, category_id) VALUES
                                                                                ('Rock Concert', 'An amazing rock concert.', 50.00, '2026-04-01 19:00:00', TRUE, 1),
                                                                                ('Tech Conference', 'Annual technology conference.', 200.00, '2026-05-15 09:00:00', TRUE, 2),
                                                                                ('Marathon', 'City marathon event.', 30.00, '2026-06-20 07:00:00', TRUE, 3);


INSERT INTO registrations (registration_date, user_id) VALUES
                                                           ('2026-03-10 10:00:00', (SELECT id FROM users WHERE username='alice')),
                                                           ('2026-03-11 12:00:00', (SELECT id FROM users WHERE username='bob'));


INSERT INTO registration_items (quantity, registration_id, event_id) VALUES
                                                                         (2, (SELECT id FROM registrations WHERE user_id=(SELECT id FROM users WHERE username='alice')), 1),
                                                                         (1, (SELECT id FROM registrations WHERE user_id=(SELECT id FROM users WHERE username='bob')), 2);