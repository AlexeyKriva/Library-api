package api.modsen.library.bookmicroservice.repositories;

import api.modsen.library.bookmicroservice.entities.library.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookStatusRepository extends JpaRepository<BookStatus, Long> {
    Optional<BookStatus> findById(long id);
    Optional<BookStatus> findByBookId(long bookId);
}
