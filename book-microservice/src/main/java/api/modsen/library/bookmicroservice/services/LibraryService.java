package api.modsen.library.bookmicroservice.services;


import api.modsen.library.bookmicroservice.entities.book.Book;
import api.modsen.library.bookmicroservice.entities.library.BookStatus;
import api.modsen.library.bookmicroservice.entities.library.BookStatusDto;
import api.modsen.library.bookmicroservice.entities.library.BookStatusMapper;
import api.modsen.library.bookmicroservice.exceptions.BookNotFoundException;
import api.modsen.library.bookmicroservice.repositories.BookStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;


import static api.modsen.library.bookmicroservice.config.LibraryAppConstants.*;


@Service
public class LibraryService {
    @Autowired
    private BookStatusRepository bookStatusRepository;
    private static final BookStatusMapper BOOK_STATUS_MAPPER = BookStatusMapper.INSTANCE;
    public List<BookStatus> findAllBookStatuses() {
        return bookStatusRepository.findAll();
    }
    @Async
    public CompletableFuture<BookStatus> addBookStatus(Book book) {
        LocalDateTime borrowAt = LocalDateTime.now();
        LocalDateTime returnAt = generateRandomReturnTime(borrowAt);


        BookStatus bookStatus = new BookStatus();
        bookStatus.setBook(book);
        bookStatus.setBorrowedAt(borrowAt);
        bookStatus.setReturnAt(returnAt);


        return CompletableFuture.completedFuture(bookStatusRepository.save(bookStatus));
    }


    private LocalDateTime generateRandomReturnTime(LocalDateTime borrowAt) {
        Random random = new Random();
        int borrowDays = random.nextInt(MAX_NUMBER_OF_DAYS_BORROW_BOOK) + 1;
        return borrowAt.plusDays(borrowDays);
    }


    public List<BookStatus> findAvailableBooks() {
        List<BookStatus> availableBooks = new ArrayList<>();
        List<BookStatus> bookStatuses = bookStatusRepository.findAll();

        for (BookStatus bookStatus: bookStatuses) {
            if (isBookAvailable(bookStatus)) {
                availableBooks.add(bookStatus);
            }
        }

        return availableBooks;
    }

    public boolean isBookAvailable(BookStatus bookStatus) {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(bookStatus.getReturnAt());
    }

    public List<BookStatus> findAvailableBooksBySpecifiedTime(LocalDateTime specifiedTime) {
        List<BookStatus> availableBooks = new ArrayList<>();
        List<BookStatus> bookStatuses = bookStatusRepository.findAll();

        for (BookStatus bookStatus: bookStatuses) {
            if (isBookAvailableBySpecifiedTime(bookStatus, specifiedTime)) {
                availableBooks.add(bookStatus);
            }
        }

        return availableBooks;
    }

    public boolean isBookAvailableBySpecifiedTime(BookStatus bookStatus, LocalDateTime specifiedTime) {
        return specifiedTime.isAfter(bookStatus.getReturnAt());
    }

    public BookStatus changeBookStatus(long bookId, BookStatusDto bookStatusDto) {
        Optional<BookStatus> bookStatusFromDb = bookStatusRepository.findByBookId(bookId);
        if (bookStatusFromDb.isPresent()) {
            BookStatus bookStatus = bookStatusFromDb.get();
            BookStatus updatedBookStatus = BOOK_STATUS_MAPPER.fromBookStatusDtoToBookStatus(bookStatusDto);
            bookStatus.setBorrowedAt(updatedBookStatus.getBorrowedAt());
            bookStatus.setReturnAt(updatedBookStatus.getReturnAt());
            return bookStatusRepository.save(bookStatus);
        }

        return bookStatusFromDb.orElseThrow(() -> new BookNotFoundException(BOOK_NOT_FOUND_MESSAGE_WITH_ID + bookId));
    }
}