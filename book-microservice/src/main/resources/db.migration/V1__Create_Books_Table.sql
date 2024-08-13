CREATE TABLE books
(
    id          SERIAL PRIMARY KEY,
    isbn        VARCHAR(50)   NOT NULL,
    title       VARCHAR(255)  NOT NULL,
    genre       VARCHAR(255)  NOT NULL,
    description VARCHAR(1000) NOT NULL,
    author      VARCHAR(255)  NOT NULL
)