package api.modsen.library.controllers;

import api.modsen.library.entities.book.Book;
import api.modsen.library.entities.book.BookDto;
import api.modsen.library.entities.book.BookUpdateDescriptionDto;
import api.modsen.library.services.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import static api.modsen.library.config.LibraryAppConstants.*;

import java.util.List;

@RestController
@RequestMapping(value = "/books", produces = "application/json")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.findAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        return ResponseEntity.ok(bookService.findBookById(id));
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@Valid
                                                  @PathVariable("isbn")
                                                  @Pattern(regexp = ISBN_FORMAT,
                                                          message = INVALID_ISBN_MESSAGE) String isbn) {
        return ResponseEntity.ok(bookService.findBookByIsbn(isbn));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Book> addBook(@Valid
                                            @RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.addBook(bookDto));
    }

    @PatchMapping("/{id}/description")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Book> updateBookById(@PathVariable("id") long id,
                                               @Valid
                                               @RequestBody BookUpdateDescriptionDto bookUpdateDescriptionDto) {
        return ResponseEntity.ok(bookService.updateGenreAndDescription(id, bookUpdateDescriptionDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Book> deleteBookById(@PathVariable("id") long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/isbn/{isbn}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Book> deleteBookByIsbn(@Pattern(regexp = ISBN_FORMAT,
            message = INVALID_ISBN_MESSAGE) @Valid
                                                     @PathVariable("isbn")
                                                      String isbn) {
        bookService.deleteBookByIsbn(isbn);
        return ResponseEntity.noContent().build();
    }
}