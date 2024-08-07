package api.modsen.library.repositories;

import api.modsen.library.entities.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findById(long id);
    Optional<Book> findByIsbn(String isbn);
    Book save(Book book);
    Boolean existsByIsbn(String isbn);
    Boolean existsById(long id);
    void deleteByIsbn(String isbn);
    void deleteById(long id);
}
