package api.modsen.library.services;

import api.modsen.library.entities.Book;
import api.modsen.library.entities.BookDto;
import api.modsen.library.entities.BookMapper;
import api.modsen.library.entities.BookUpdateDescriptionDto;
import api.modsen.library.exceptions.BookNotFoundException;
import api.modsen.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    private static final BookMapper BOOK_MAPPER = BookMapper.INSTANCE;

    public Book findBookById(long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found with id:" + id));
    }

    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException("Book not found with isbn:" + isbn));
    }

    public Book addBook(BookDto bookDto) {
        Book book = BOOK_MAPPER.fromBookDtoToBook(bookDto);
        return bookRepository.save(book);
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

        return bookFromDb.orElseThrow(() -> new BookNotFoundException("Book not found with id:" + id));
    }
}