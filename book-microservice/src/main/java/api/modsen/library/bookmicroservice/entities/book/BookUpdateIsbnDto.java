package api.modsen.library.bookmicroservice.entities.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static api.modsen.library.bookmicroservice.config.LibraryAppConstants.*;

public class BookUpdateIsbnDto {
    @JsonProperty("isbn")
    @Pattern(regexp = ISBN_FORMAT, message = INVALID_ISBN_MESSAGE)
    @NotBlank(message = BLANK_ISBN_MESSAGE)
    private String isbn;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
