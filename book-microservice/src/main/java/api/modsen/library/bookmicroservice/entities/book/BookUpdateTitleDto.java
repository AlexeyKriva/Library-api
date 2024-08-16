package api.modsen.library.bookmicroservice.entities.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import static api.modsen.library.bookmicroservice.config.LibraryAppConstants.*;

public class BookUpdateTitleDto {
    @JsonProperty("title")
    @NotBlank(message = BLANK_TITLE_MESSAGE)
    @Size(max = MAX_LENGTH_OF_BOOK_TITLE, message = MAX_LENGTH_OF_BOOK_TITLE_MESSAGE)
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
