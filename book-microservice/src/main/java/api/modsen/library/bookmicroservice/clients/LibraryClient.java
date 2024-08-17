package api.modsen.library.bookmicroservice.clients;

import api.modsen.library.bookmicroservice.entities.book.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "library-microservice", url = "http://localhost:8765/library/")
public interface LibraryClient {
    @PostMapping("/status")
    String addBookStatus(Book book);
}
