package api.modsen.library.bookmicroservice.entities.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static api.modsen.library.bookmicroservice.config.LibraryAppConstants.*;

@Setter
@Getter
public class BookUpdateDescriptionDto {
    @JsonProperty("description")
    @NotBlank(message = BLANK_DESCRIPTION_MESSAGE)
    @Size(max = MAX_LENGTH_OF_BOOK_DESCRIPTION, message = MAX_LENGTH_OF_BOOK_DESCRIPTION_MESSAGE)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
