package api.modsen.library.controllers;

import api.modsen.library.entities.Book;
import api.modsen.library.entities.BookDto;
import api.modsen.library.entities.BookUpdateDescriptionDto;
import api.modsen.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/library", produces = "application/json")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/all-books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.findAllBooks());
    }

    @GetMapping("/book/by-id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        return ResponseEntity.ok(bookService.findBookById(id));
    }

    @GetMapping("/book/by-isbn/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable("isbn") String isbn) {
        return ResponseEntity.ok(bookService.findBookByIsbn(isbn));
    }

    @PostMapping("/add-book")
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.addBook(bookDto));
    }

    @PatchMapping("/update-book/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable("id") long id,
                                               @RequestBody BookUpdateDescriptionDto bookUpdateDescriptionDto) {
        return ResponseEntity.ok(bookService.updateGenreAndDescription(id, bookUpdateDescriptionDto));
    }

    @DeleteMapping("/delete-book/by-id/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable("id") long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-book/by-isbn/{isbn}")
    public ResponseEntity<Book> deleteBookByIsbn(@PathVariable("isbn") String isbn) {
        bookService.deleteBookByIsbn(isbn);
        return ResponseEntity.noContent().build();
    }
}