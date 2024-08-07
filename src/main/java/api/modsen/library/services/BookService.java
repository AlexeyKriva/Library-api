package api.modsen.library.services;

import api.modsen.library.entities.book.Book;
import api.modsen.library.entities.book.BookDto;
import api.modsen.library.entities.book.BookMapper;
import api.modsen.library.entities.book.BookUpdateDescriptionDto;
import api.modsen.library.entities.library.BookStatus;
import api.modsen.library.exceptions.BookNotFoundException;
import api.modsen.library.repositories.BookRepository;
import api.modsen.library.repositories.BookStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static api.modsen.library.config.LibraryAppConstants.BOOK_NOT_FOUND_MESSAGE_WITH_ID;
import static api.modsen.library.config.LibraryAppConstants.BOOK_NOT_FOUND_MESSAGE_WITH_ISBN;

@Service
@Transactional
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    private static final BookMapper BOOK_MAPPER = BookMapper.INSTANCE;
    @Autowired
    private LibraryService libraryService;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(BOOK_NOT_FOUND_MESSAGE_WITH_ID + id));
    }

    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(BOOK_NOT_FOUND_MESSAGE_WITH_ISBN + isbn));
    }

    public Book addBook(BookDto bookDto) {
        Book newBook = BOOK_MAPPER.fromBookDtoToBook(bookDto);
        Book bookFromDb = bookRepository.save(newBook);
        libraryService.addBookStatus(bookFromDb);
        return bookFromDb;
    }

    public void deleteBookByIsbn(String isbn) {
        if (bookRepository.existsByIsbn(isbn)) {
            bookRepository.deleteByIsbn(isbn);
        }
    }

    public void deleteBookById(long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        }
    }

    public Book updateGenreAndDescription(long id, BookUpdateDescriptionDto bookUpdateInformationDto) {
        Optional<Book> bookFromDb = bookRepository.findById(id);
        if (bookFromDb.isPresent()) {
            Book updatingBook = bookFromDb.get();
            updatingBook.setDescription(bookUpdateInformationDto.getDescription());

            return bookRepository.save(updatingBook);
        }

        return bookFromDb.orElseThrow(() -> new BookNotFoundException(BOOK_NOT_FOUND_MESSAGE_WITH_ID + id));
    }
}