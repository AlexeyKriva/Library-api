package api.modsen.library.repositories;

import api.modsen.library.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Long, Book> {
    Optional<Book> findById(long id);
    Optional<Book> findByIsbn(String isbn);
    Book save(Book book);
    Boolean existsByIsbn(String isbn);
    void deleteByIsbn(String isbn);
}
