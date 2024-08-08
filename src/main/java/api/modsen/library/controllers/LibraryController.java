package api.modsen.library.controllers;

import api.modsen.library.entities.library.BookStatus;
import api.modsen.library.entities.library.BookStatusDto;
import api.modsen.library.services.LibraryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/library", produces = "application/json")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @GetMapping("/book-statuses")
    public ResponseEntity<List<BookStatus>> getBookStatuses() {
        return ResponseEntity.ok(libraryService.findAllBookStatuses());
    }

    @GetMapping("/available-books-statuses")
    public ResponseEntity<List<BookStatus>> getAvailableBooksStatuses() {
        return ResponseEntity.ok(libraryService.findAvailableBooks());
    }

    @GetMapping("/available-books-statuses/by-specified-time")
    public ResponseEntity<List<BookStatus>> getAvailableBooksStatusesBySpecifiedTime(@Valid
                                                                                        @RequestParam("specifiedTime")
                                                                                    LocalDateTime specifiedTime) {
        return ResponseEntity.ok(libraryService.findAvailableBooksBySpecifiedTime(specifiedTime));
    }

    @PatchMapping("/change-book-status/{bookId}")
    public ResponseEntity<BookStatus> changeBookStatusByBookId(@PathVariable("bookId") long bookId,
                                                               @Validated
                                                               @RequestBody BookStatusDto bookStatusDto) {
        return ResponseEntity.ok(libraryService.changeBookStatus(bookId, bookStatusDto));
    }
}
