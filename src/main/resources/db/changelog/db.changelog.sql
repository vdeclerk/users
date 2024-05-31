-- liquibase formatted sql

-- changeset victor.declerk:1

CREATE TABLE users (
    user_id     SERIAL PRIMARY KEY,
    name        VARCHAR(250) NOT NULL,
    email       VARCHAR(1024) NOT NULL UNIQUE,
    password    VARCHAR(250) NOT NULL,
    user_ref    CHAR(36) NOT NULL UNIQUE,
    created     TIMESTAMP NOT NULL,
    modified    TIMESTAMP NOT NULL,
    last_login  TIMESTAMP NOT NULL,
    token       TEXT,
    active      BOOLEAN,
    salt        CHAR(36)
);

CREATE TABLE phones (
    phone_id     SERIAL PRIMARY KEY,
    number       VARCHAR(20),
    city_code    VARCHAR(10),
    country_code CHAR(10),
    user_id      INTEGER NOT NULL REFERENCES users
);