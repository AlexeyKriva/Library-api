ALTER TABLE books
    ADD CONSTRAINT unique_isbn UNIQUE (isbn);