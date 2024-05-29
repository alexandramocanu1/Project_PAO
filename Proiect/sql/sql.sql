commit;

DELETE FROM Tickets;
DELETE FROM Reviews;
DELETE FROM Reservations;
DELETE FROM Users;
DELETE FROM Venues;
DELETE FROM Discounts;

SELECT * FROM Events;
DELETE FROM Events;
DROP TABLE Events;


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





