package api.modsen.library.bookmicroservice.entities.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import static api.modsen.library.bookmicroservice.config.LibraryAppConstants.*;

public class BookUpdateGenreDto {
    @JsonProperty("genre")
    @NotBlank(message = BLANK_GENRE_MESSAGE)
    @Size(max = MAX_LENGTH_OF_BOOK_GENRE_AND_AUTHOR, message = MAX_LENGTH_OF_BOOK_GENRE_AND_AUTHOR_MESSAGE)
    private String genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
