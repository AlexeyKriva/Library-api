package api.modsen.library.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookUpdateDescriptionDto {
    @JsonProperty("description")
    @NotBlank(message = "Description cannot be blank")
    private String description;
}
