package api.modsen.library.bookmicroservice.controllers;

import api.modsen.library.bookmicroservice.entities.book.*;
import api.modsen.library.bookmicroservice.services.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static api.modsen.library.bookmicroservice.config.LibraryAppConstants.*;

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
    public ResponseEntity<Book> addBook(@Valid
                                            @RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.addBook(bookDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable("id") long id,
                                               @Valid
                                               @RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.updateBook(id, bookDto));
    }

    @PatchMapping("/{id}/isbn")
    public ResponseEntity<Book> updateBookIsbnById(@PathVariable("id") long id,
                                                    @Valid
                                                    @RequestBody BookUpdateIsbnDto bookUpdateIsbnDto) {
        return ResponseEntity.ok(bookService.updateBookIsbn(id, bookUpdateIsbnDto));
    }

    @PatchMapping("/{id}/title")
    public ResponseEntity<Book> updateBookTitleById(@PathVariable("id") long id,
                                                    @Valid
                                                    @RequestBody BookUpdateTitleDto bookUpdateTitleDto) {
        return ResponseEntity.ok(bookService.updateBookTitle(id, bookUpdateTitleDto));
    }

    @PatchMapping("/{id}/genre")
    public ResponseEntity<Book> updateBookGenreById(@PathVariable("id") long id,
                                                    @Valid
                                                    @RequestBody BookUpdateGenreDto bookUpdateGenreDto) {
        return ResponseEntity.ok(bookService.updateBookGenre(id, bookUpdateGenreDto));
    }

    @PatchMapping("/{id}/description")
    public ResponseEntity<Book> updateBookDescriptionById(@PathVariable("id") long id,
                                               @Valid
                                               @RequestBody BookUpdateDescriptionDto bookUpdateDescriptionDto) {
        return ResponseEntity.ok(bookService.updateBookDescription(id, bookUpdateDescriptionDto));
    }

    @PatchMapping("/{id}/author")
    public ResponseEntity<Book> updateBookAuthorById(@PathVariable("id") long id,
                                                     @Valid
                                                     @RequestBody BookUpdateAuthorDto bookUpdateAuthorDto) {
        return ResponseEntity.ok(bookService.updateBookAuthor(id, bookUpdateAuthorDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable("id") long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/isbn/{isbn}")
    public ResponseEntity<Book> deleteBookByIsbn(@Pattern(regexp = ISBN_FORMAT,
            message = INVALID_ISBN_MESSAGE) @Valid
                                                     @PathVariable("isbn")
                                                      String isbn) {
        bookService.deleteBookByIsbn(isbn);
        return ResponseEntity.noContent().build();
    }
}