-- Crearea tabelului Users
CREATE TABLE Users (
                       id NUMBER PRIMARY KEY,
                       username VARCHAR2(50) NOT NULL,
                       password VARCHAR2(50) NOT NULL,
                       email VARCHAR2(100) NOT NULL,
                       full_name VARCHAR2(100) NOT NULL,
                       role VARCHAR2(20) NOT NULL
);

-- Crearea tabelului Venues
CREATE TABLE Venues (
                        id NUMBER PRIMARY KEY,
                        name VARCHAR2(100) NOT NULL,
                        location VARCHAR2(200) NOT NULL,
                        capacity NUMBER NOT NULL
);

-- Crearea tabelului Events
CREATE TABLE Events (
                        id NUMBER PRIMARY KEY,
                        name VARCHAR2(100) NOT NULL,
                        type VARCHAR2(20) CHECK (type IN ('CONCERT', 'CONFERENCE', 'SPORT', 'THEATER')),
                        event_date DATE NOT NULL,
                        venue_id NUMBER,
                        FOREIGN KEY (venue_id) REFERENCES Venues(id)
);

-- Crearea tabelului Tickets
CREATE TABLE Tickets (
                         id NUMBER PRIMARY KEY,
                         event_id NUMBER,
                         user_id NUMBER,
                         price NUMBER(10, 2) NOT NULL,
                         seat VARCHAR2(10),
                         FOREIGN KEY (event_id) REFERENCES Events(id),
                         FOREIGN KEY (user_id) REFERENCES Users(id)
);

-- Crearea tabelului Reviews
CREATE TABLE Reviews (
                         id NUMBER PRIMARY KEY,
                         event_id NUMBER,
                         user_id NUMBER,
                         rating NUMBER CHECK (rating >= 1 AND rating <= 5),
                         "COMMENT" CLOB,
                         FOREIGN KEY (event_id) REFERENCES Events(id),
                         FOREIGN KEY (user_id) REFERENCES Users(id)
);

-- Crearea tabelului Reservations
CREATE TABLE Reservations (
                              id NUMBER PRIMARY KEY,
                              user_id NUMBER,
                              event_id NUMBER,
                              reservation_date DATE NOT NULL,
                              status VARCHAR2(20) CHECK (status IN ('CONFIRMED', 'CANCELLED', 'PENDING')),
                              FOREIGN KEY (user_id) REFERENCES Users(id),
                              FOREIGN KEY (event_id) REFERENCES Events(id)
);

-- Crearea tabelului Discounts
CREATE TABLE Discounts (
                           id NUMBER PRIMARY KEY,
                           code VARCHAR2(50) NOT NULL,
                           percentage NUMBER(5, 2) NOT NULL,
                           valid_until DATE NOT NULL
);



-- Crearea secvențelor pentru generarea automată a ID-urilor
CREATE SEQUENCE users_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE venues_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE events_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE tickets_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE reviews_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE reservations_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE discounts_seq START WITH 1 INCREMENT BY 1;



-- Crearea trigger-elor pentru a seta automat ID-urile la inserare
CREATE OR REPLACE TRIGGER users_bir
    BEFORE INSERT ON Users
    FOR EACH ROW
BEGIN
    SELECT users_seq.NEXTVAL INTO :NEW.id FROM dual;
END;

CREATE OR REPLACE TRIGGER venues_bir
    BEFORE INSERT ON Venues
    FOR EACH ROW
BEGIN
    SELECT venues_seq.NEXTVAL INTO :NEW.id FROM dual;
END;

CREATE OR REPLACE TRIGGER events_bir
    BEFORE INSERT ON Events
    FOR EACH ROW
BEGIN
    SELECT events_seq.NEXTVAL INTO :NEW.id FROM dual;
END;

CREATE OR REPLACE TRIGGER tickets_bir
    BEFORE INSERT ON Tickets
    FOR EACH ROW
BEGIN
    SELECT tickets_seq.NEXTVAL INTO :NEW.id FROM dual;
END;

CREATE OR REPLACE TRIGGER reviews_bir
    BEFORE INSERT ON Reviews
    FOR EACH ROW
BEGIN
    SELECT reviews_seq.NEXTVAL INTO :NEW.id FROM dual;
END;

CREATE OR REPLACE TRIGGER reservations_bir
    BEFORE INSERT ON Reservations
    FOR EACH ROW
BEGIN
    SELECT reservations_seq.NEXTVAL INTO :NEW.id FROM dual;
END;

CREATE OR REPLACE TRIGGER discounts_bir
    BEFORE INSERT ON Discounts
    FOR EACH ROW
BEGIN
    SELECT discounts_seq.NEXTVAL INTO :NEW.id FROM dual;
END;

