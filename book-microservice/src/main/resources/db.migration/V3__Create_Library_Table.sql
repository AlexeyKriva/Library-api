CREATE TABLE library
(
    id          SERIAL PRIMARY KEY,
    book_id     BIGINT    NOT NULL,
    borrowed_at TIMESTAMP NOT NULL,
    return_at   TIMESTAMP NOT NULL,
    CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE CASCADE
)