package api.modsen.library.services;

import api.modsen.library.entities.book.Book;
import api.modsen.library.entities.library.BookStatus;
import api.modsen.library.repositories.BookStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {
    @Autowired
    private BookStatusRepository bookStatusRepository;

    public BookStatus addBookStatus(Book book) {

    }
}
