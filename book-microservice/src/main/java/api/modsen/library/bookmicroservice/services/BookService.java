package api.modsen.library.bookmicroservice.services;

import api.modsen.library.bookmicroservice.entities.book.*;
import api.modsen.library.bookmicroservice.repositories.BookRepository;
import api.modsen.library.bookmicroservice.exceptions.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static api.modsen.library.bookmicroservice.config.LibraryAppConstants.BOOK_NOT_FOUND_MESSAGE_WITH_ID;
import static api.modsen.library.bookmicroservice.config.LibraryAppConstants.BOOK_NOT_FOUND_MESSAGE_WITH_ISBN;

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

    public Book updateBook(long id, BookDto bookDto) {
        Optional<Book> bookFromDb = bookRepository.findById(id);
        if (bookFromDb.isPresent()) {
            Book updatingBook = bookFromDb.get();
            BOOK_MAPPER.updateBookFromBookDto(bookDto, updatingBook);

            return bookRepository.save(updatingBook);
        }

        return bookFromDb.orElseThrow(() -> new BookNotFoundException(BOOK_NOT_FOUND_MESSAGE_WITH_ID + id));
    }

    public Book updateBookIsbn(long id, BookUpdateIsbnDto bookUpdateIsbnDto) {
        Optional<Book> bookFromDb = bookRepository.findById(id);
        if (bookFromDb.isPresent()) {
            Book updatingBook = bookFromDb.get();
            updatingBook.setIsbn(bookUpdateIsbnDto.getIsbn());

            return bookRepository.save(updatingBook);
        }

        return bookFromDb.orElseThrow(() -> new BookNotFoundException(BOOK_NOT_FOUND_MESSAGE_WITH_ID + id));
    }

    public Book updateBookTitle(long id, BookUpdateTitleDto bookUpdateTitleDto) {
        Optional<Book> bookFromDb = bookRepository.findById(id);
        if (bookFromDb.isPresent()) {
            Book updatingBook = bookFromDb.get();
            updatingBook.setTitle(bookUpdateTitleDto.getTitle());

            return bookRepository.save(updatingBook);
        }

        return bookFromDb.orElseThrow(() -> new BookNotFoundException(BOOK_NOT_FOUND_MESSAGE_WITH_ID + id));
    }

    public Book updateBookGenre(long id, BookUpdateGenreDto bookUpdateGenreDto) {
        Optional<Book> bookFromDb = bookRepository.findById(id);
        if (bookFromDb.isPresent()) {
            Book updatingBook = bookFromDb.get();
            updatingBook.setGenre(bookUpdateGenreDto.getGenre());

            return bookRepository.save(updatingBook);
        }

        return bookFromDb.orElseThrow(() -> new BookNotFoundException(BOOK_NOT_FOUND_MESSAGE_WITH_ID + id));
    }

    public Book updateBookDescription(long id, BookUpdateDescriptionDto bookUpdateInformationDto) {
        Optional<Book> bookFromDb = bookRepository.findById(id);
        if (bookFromDb.isPresent()) {
            Book updatingBook = bookFromDb.get();
            updatingBook.setDescription(bookUpdateInformationDto.getDescription());

            return bookRepository.save(updatingBook);
        }

        return bookFromDb.orElseThrow(() -> new BookNotFoundException(BOOK_NOT_FOUND_MESSAGE_WITH_ID + id));
    }

    public Book updateBookAuthor(long id, BookUpdateAuthorDto bookUpdateAuthorDto) {
        Optional<Book> bookFromDb = bookRepository.findById(id);
        if (bookFromDb.isPresent()) {
            Book updatingBook = bookFromDb.get();
            updatingBook.setAuthor(bookUpdateAuthorDto.getAuthor());

            return bookRepository.save(updatingBook);
        }

        return bookFromDb.orElseThrow(() -> new BookNotFoundException(BOOK_NOT_FOUND_MESSAGE_WITH_ID + id));
    }
}