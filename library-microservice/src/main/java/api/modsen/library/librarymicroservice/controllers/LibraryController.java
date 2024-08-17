package api.modsen.library.librarymicroservice.controllers;

import api.modsen.library.librarymicroservice.entities.library.Book;
import api.modsen.library.librarymicroservice.entities.library.BookStatusDto;
import api.modsen.library.librarymicroservice.services.LibraryService;
import api.modsen.library.librarymicroservice.entities.library.BookStatus;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/library", produces = "application/json")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @GetMapping("/statuses")
    public ResponseEntity<List<BookStatus>> getBookStatuses() {
        return ResponseEntity.ok(libraryService.findAllBookStatuses());
    }

    @PostMapping("/status")
    public ResponseEntity<String> addBookStatus(@Valid @RequestBody Book book) {
        System.out.println("======================================================");
        System.out.println(book);
        System.out.println("======================================================");
        libraryService.addBookStatus(book);
        return ResponseEntity.ok("Book status successfully added");
    }

    @GetMapping("/available")
    public ResponseEntity<List<BookStatus>> getAvailableBooksStatuses() {
        return ResponseEntity.ok(libraryService.findAvailableBooks());
    }

    @GetMapping("/available/by-time")
    public ResponseEntity<List<BookStatus>> getAvailableBooksStatusesBySpecifiedTime(@Valid
                                                                                        @RequestParam("specifiedTime")
                                                                                    LocalDateTime specifiedTime) {
        return ResponseEntity.ok(libraryService.findAvailableBooksBySpecifiedTime(specifiedTime));
    }

    @PatchMapping("/{bookId}/status")
    public ResponseEntity<BookStatus> changeBookStatusByBookId(@PathVariable("bookId") long bookId,
                                                               @Validated
                                                               @RequestBody BookStatusDto bookStatusDto) {
        return ResponseEntity.ok(libraryService.changeBookStatus(bookId, bookStatusDto));
    }
}
