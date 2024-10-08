package api.modsen.library.bookmicroservice.entities.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import static api.modsen.library.bookmicroservice.config.LibraryAppConstants.*;

@Setter
@Getter
@ToString
public class BookDto {
    @JsonProperty("isbn")
    @Pattern(regexp = ISBN_FORMAT, message = INVALID_ISBN_MESSAGE)
    @NotBlank(message = BLANK_ISBN_MESSAGE)
    private String isbn;

    @JsonProperty("title")
    @NotBlank(message = BLANK_TITLE_MESSAGE)
    @Size(max = MAX_LENGTH_OF_BOOK_TITLE, message = MAX_LENGTH_OF_BOOK_TITLE_MESSAGE)
    private String title;

    @JsonProperty("genre")
    @NotBlank(message = BLANK_GENRE_MESSAGE)
    @Size(max = MAX_LENGTH_OF_BOOK_GENRE_AND_AUTHOR, message = MAX_LENGTH_OF_BOOK_GENRE_AND_AUTHOR_MESSAGE)
    private String genre;

    @JsonProperty("description")
    @NotBlank(message = BLANK_DESCRIPTION_MESSAGE)
    @Size(max = MAX_LENGTH_OF_BOOK_DESCRIPTION, message = MAX_LENGTH_OF_BOOK_DESCRIPTION_MESSAGE)
    private String description;

    @JsonProperty("author")
    @NotBlank(message = BLANK_AUTHOR_MESSAGE)
    @Size(max = MAX_LENGTH_OF_BOOK_GENRE_AND_AUTHOR, message = MAX_LENGTH_OF_BOOK_GENRE_AND_AUTHOR_MESSAGE)
    private String author;

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}