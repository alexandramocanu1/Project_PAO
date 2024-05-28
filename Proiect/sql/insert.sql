-- Insert users
INSERT INTO Users (username, password, email, full_name) VALUES
    ('alexandra', 'parola', 'alexandramocanu181@yahoo.com', 'Alexandra Mocanu');
INSERT INTO Users (username, password, email, full_name) VALUES
    ('johndoe', 'password123', 'john@example.com', 'John Doe');
INSERT INTO Users (username, password, email, full_name) VALUES
    ('janedoe', 'password456', 'jane@example.com', 'Jane Doe');
INSERT INTO Users (username, password, email, full_name) VALUES
    ('alice', 'password789', 'alice@example.com', 'Alice Smith');

-- Insert venues
INSERT INTO Venues (name, location, capacity) VALUES
    ('Stadium', '123 Main St, Anytown', 50000);
INSERT INTO Venues (name, location, capacity) VALUES
    ('Conference Hall', '456 High St, Metropolis', 2000);
INSERT INTO Venues (name, location, capacity) VALUES
    ('Theater', '789 Broadway, Cityville', 1500);

-- Insert events
INSERT INTO Events (name, type, event_date, venue_id) VALUES
    ('Rock Concert', 'CONCERT', TO_DATE('2024-07-10', 'YYYY-MM-DD'), 1);
INSERT INTO Events (name, type, event_date, venue_id) VALUES
    ('Tech Conference', 'CONFERENCE', TO_DATE('2024-08-15', 'YYYY-MM-DD'), 2);
INSERT INTO Events (name, type, event_date, venue_id) VALUES
    ('Broadway Show', 'THEATER', TO_DATE('2024-09-20', 'YYYY-MM-DD'), 3);

-- Insert tickets
INSERT INTO Tickets (event_id, user_id, price, seat) VALUES
    (1, 1, 50.00, 'A1');
INSERT INTO Tickets (event_id, user_id, price, seat) VALUES
    (2, 2, 150.00, 'B2');
INSERT INTO Tickets (event_id, user_id, price, seat) VALUES
    (3, 3, 75.00, 'C3');

-- Insert reviews
INSERT INTO Reviews (id, event_id, user_id, rating, "COMMENT") VALUES
    (1, 1, 1, 5, 'Amazing concert!');
INSERT INTO Reviews (id, event_id, user_id, rating, "COMMENT") VALUES
    (2, 2, 2, 4, 'Very informative.');
INSERT INTO Reviews (id, event_id, user_id, rating, "COMMENT") VALUES
    (3, 3, 3, 3, 'Good performance.');


-- Insert reservations
INSERT INTO Reservations (user_id, event_id, reservation_date, status) VALUES
    (1, 1, TO_DATE('2024-06-01', 'YYYY-MM-DD'), 'CONFIRMED');
INSERT INTO Reservations (user_id, event_id, reservation_date, status) VALUES
    (2, 2, TO_DATE('2024-07-01', 'YYYY-MM-DD'), 'PENDING');
INSERT INTO Reservations (user_id, event_id, reservation_date, status) VALUES
    (3, 3, TO_DATE('2024-08-01', 'YYYY-MM-DD'), 'CANCELLED');

-- Insert discounts
INSERT INTO Discounts (code, percentage, valid_until) VALUES
    ('SUMMER20', 20.00, TO_DATE('2024-09-01', 'YYYY-MM-DD'));
INSERT INTO Discounts (code, percentage, valid_until) VALUES
    ('EARLYBIRD', 15.00, TO_DATE('2024-06-30', 'YYYY-MM-DD'));
INSERT INTO Discounts (code, percentage, valid_until) VALUES
    ('LASTMINUTE', 10.00, TO_DATE('2024-12-31', 'YYYY-MM-DD'));
