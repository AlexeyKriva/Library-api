package api.modsen.library.services;


import api.modsen.library.entities.book.Book;
import api.modsen.library.entities.library.BookStatus;
import api.modsen.library.repositories.BookStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;


import static api.modsen.library.config.LibraryAppConstants.MAX_NUMBER_OF_DAYS_BORROW_BOOK;


@Service
public class LibraryService {
    @Autowired
    private BookStatusRepository bookStatusRepository;


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
}


