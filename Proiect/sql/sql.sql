commit;

DELETE FROM Tickets;
DELETE FROM Reviews;
DELETE FROM Reservations;
DELETE FROM Users;
DELETE FROM Venues;
DELETE FROM Discounts;


SELECT * FROM Users;

SELECT * FROM Events;
DELETE FROM Events;
DROP TABLE USERS;


DELETE FROM Tickets WHERE event_id IN (SELECT id FROM Events);

SELECT constraint_name
FROM user_constraints
WHERE table_name = 'EVENTS'
  AND constraint_type = 'R';

ALTER TABLE Events DISABLE CONSTRAINT SYS_C007014;

SELECT s.sid, s.serial#, s.username, l.OBJECT_ID, o.OBJECT_NAME, o.OBJECT_TYPE
FROM v$lock l
         JOIN v$session s ON l.SID = s.SID
         JOIN dba_objects o ON l.id1 = o.OBJECT_ID
WHERE l.block = 1;

ALTER SYSTEM KILL SESSION 'sid,serial#';


-- Drop constraints to avoid errors during table drop
ALTER TABLE Tickets DROP CONSTRAINT tickets_event_fk;
ALTER TABLE Tickets DROP CONSTRAINT tickets_user_fk;
ALTER TABLE Reviews DROP CONSTRAINT reviews_event_fk;
ALTER TABLE Reviews DROP CONSTRAINT reviews_user_fk;
ALTER TABLE Reservations DROP CONSTRAINT reservations_user_fk;
ALTER TABLE Reservations DROP CONSTRAINT reservations_event_fk;
ALTER TABLE Events DROP CONSTRAINT events_venue_fk;

-- Drop tables
DROP TABLE Discounts CASCADE CONSTRAINTS;
DROP TABLE Reservations CASCADE CONSTRAINTS;
DROP TABLE Reviews CASCADE CONSTRAINTS;
DROP TABLE Tickets CASCADE CONSTRAINTS;
DROP TABLE Events CASCADE CONSTRAINTS;
DROP TABLE Venues CASCADE CONSTRAINTS;
DROP TABLE Users CASCADE CONSTRAINTS;

-- Drop sequences
DROP SEQUENCE users_seq;
DROP SEQUENCE venues_seq;
DROP SEQUENCE events_seq;
DROP SEQUENCE tickets_seq;
DROP SEQUENCE reviews_seq;
DROP SEQUENCE reservations_seq;
DROP SEQUENCE discounts_seq;

-- Drop triggers
DROP TRIGGER users_bir;
DROP TRIGGER venues_bir;
DROP TRIGGER events_bir;
DROP TRIGGER tickets_bir;
DROP TRIGGER reviews_bir;
DROP TRIGGER reservations_bir;
DROP TRIGGER discounts_bir;






SELECT COLUMN_NAME
FROM ALL_TAB_COLUMNS
WHERE TABLE_NAME = 'DISCOUNTS'


ALTER TABLE Users ADD role VARCHAR2(20) NOT NULL;


