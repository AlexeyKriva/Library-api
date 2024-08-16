package api.modsen.library.bookmicroservice.entities.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import static api.modsen.library.bookmicroservice.config.LibraryAppConstants.*;

public class BookUpdateAuthorDto {
    @JsonProperty("author")
    @NotBlank(message = BLANK_AUTHOR_MESSAGE)
    @Size(max = MAX_LENGTH_OF_BOOK_GENRE_AND_AUTHOR, message = MAX_LENGTH_OF_BOOK_GENRE_AND_AUTHOR_MESSAGE)
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
