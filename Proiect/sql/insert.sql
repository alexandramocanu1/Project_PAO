INSERT INTO Users (username, password, email, full_name, role) VALUES
    ('alexandra', 'parola', 'alexandramocanu181@yahoo.com', 'Alexandra Mocanu', 'admin');
INSERT INTO Users (username, password, email, full_name, role) VALUES
    ('mocanu', 'mocanu', 'mocanu@info.ro', 'Mocanu Mocanu', 'user');
INSERT INTO Users (username, password, email, full_name, role) VALUES
    ('paula', 'paula', 'paula@info.ro', 'Paula Paula', 'user');


INSERT INTO Admins (user_id, "level") VALUES
    ((SELECT id FROM Users WHERE username='alexandra'), 'superadmin');

INSERT INTO RegularUsers (user_id, subscription_type) VALUES
    ((SELECT id FROM Users WHERE username='mocanu'), 'basic');
INSERT INTO RegularUsers (user_id, subscription_type) VALUES
((SELECT id FROM Users WHERE username='paula'), 'premium');

INSERT INTO Venues (name, location, capacity) VALUES
    ('Stadium', '123 Main St, Anytown', 50000);
INSERT INTO Venues (name, location, capacity) VALUES
    ('Conference Hall', '456 High St, Metropolis', 2000);
INSERT INTO Venues (name, location, capacity) VALUES
    ('Theater', '789 Broadway, Cityville', 1500);

INSERT INTO Events (name, type, event_date, venue_id) VALUES
    ('Rock Concert', 'CONCERT', TO_DATE('2024-07-10', 'YYYY-MM-DD'), 1);
INSERT INTO Events (name, type, event_date, venue_id) VALUES
    ('Tech Conference', 'CONFERENCE', TO_DATE('2024-08-15', 'YYYY-MM-DD'), 2);
INSERT INTO Events (name, type, event_date, venue_id) VALUES
    ('Broadway Show', 'THEATER', TO_DATE('2024-09-20', 'YYYY-MM-DD'), 3);

INSERT INTO Tickets (event_id, user_id, price, seat) VALUES
    (1, 1, 50.00, 'A1');
INSERT INTO Tickets (event_id, user_id, price, seat) VALUES
    (2, 2, 150.00, 'B2');
INSERT INTO Tickets (event_id, user_id, price, seat) VALUES
    (3, 1, 75.00, 'C3');

INSERT INTO Reviews (event_id, user_id, rating, "COMMENT") VALUES
    (1, 1, 5, 'Amazing concert!');
INSERT INTO Reviews (event_id, user_id, rating, "COMMENT") VALUES
    (2, 2, 4, 'Very informative.');
INSERT INTO Reviews (event_id, user_id, rating, "COMMENT") VALUES
    (3, 1, 3, 'Good performance.');

INSERT INTO Reservations (user_id, event_id, reservation_date, status) VALUES
    (1, 1, TO_DATE('2024-06-01', 'YYYY-MM-DD'), 'CONFIRMED');
INSERT INTO Reservations (user_id, event_id, reservation_date, status) VALUES
    (2, 2, TO_DATE('2024-07-01', 'YYYY-MM-DD'), 'PENDING');
INSERT INTO Reservations (user_id, event_id, reservation_date, status) VALUES
    (3, 3, TO_DATE('2024-08-01', 'YYYY-MM-DD'), 'CANCELLED');  -- Atribuit user_id 1 (Alexandra) deoarece user_id 3 nu există

INSERT INTO Discounts (code, percentage, valid_until) VALUES
    ('SUMMER20', 20.00, TO_DATE('2024-09-01', 'YYYY-MM-DD'));
INSERT INTO Discounts (code, percentage, valid_until) VALUES
    ('EARLYBIRD', 15.00, TO_DATE('2024-06-30', 'YYYY-MM-DD'));
INSERT INTO Discounts (code, percentage, valid_until) VALUES
    ('LASTMINUTE', 10.00, TO_DATE('2024-12-31', 'YYYY-MM-DD'));


-- Inserarea datelor în tabelele TypeEvent
INSERT INTO TypeEvent (name) VALUES
    ('CONCERT');
INSERT INTO TypeEvent (name) VALUES
('CONFERENCE');
INSERT INTO TypeEvent (name) VALUES
('SPORT');
INSERT INTO TypeEvent (name) VALUES
('THEATER');

-- Inserarea datelor în tabelele EventDiscounts
INSERT INTO EventDiscounts (event_id, discount_id) VALUES
    ((SELECT id FROM Events WHERE name='Rock Concert'), (SELECT id FROM Discounts WHERE code='SUMMER20'));
INSERT INTO EventDiscounts (event_id, discount_id) VALUES
((SELECT id FROM Events WHERE name='Tech Conference'), (SELECT id FROM Discounts WHERE code='EARLYBIRD'));
INSERT INTO EventDiscounts (event_id, discount_id) VALUES
((SELECT id FROM Events WHERE name='Broadway Show'), (SELECT id FROM Discounts WHERE code='LASTMINUTE'));