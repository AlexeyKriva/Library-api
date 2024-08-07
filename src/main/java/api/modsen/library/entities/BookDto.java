package api.modsen.library.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Setter
@Getter
@ToString
public class BookDto {
    @JsonProperty("isbn")
    @Pattern(regexp = "^(?:978|979)-\\d{1,5}-\\d{1,7}-\\d{1,7}-\\d$", message = "Invalid isbn format")
    private String isbn;
    @JsonProperty("title")
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @JsonProperty("genre")
    @NotBlank(message = "Genre cannot be blank")
    private String genre;
    @JsonProperty("description")
    @NotBlank(message = "Description cannot be blank")
    private String description;
    @JsonProperty("author")
    @NotBlank(message = "Author cannot be blank")
    private String author;
}