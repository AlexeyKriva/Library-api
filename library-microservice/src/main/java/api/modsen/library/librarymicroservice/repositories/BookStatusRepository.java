package api.modsen.library.librarymicroservice.repositories;

import api.modsen.library.librarymicroservice.entities.library.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookStatusRepository extends JpaRepository<BookStatus, Long> {
    Optional<BookStatus> findById(long id);
    Optional<BookStatus> findByBookId(long bookId);
}
