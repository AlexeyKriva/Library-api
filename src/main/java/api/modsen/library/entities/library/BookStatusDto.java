package api.modsen.library.entities.library;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import static api.modsen.library.config.LibraryAppConstants.BORROWED_TIME_IN_FUTURE_MESSAGE;

@Getter
@Setter
public class BookStatusDto {
    @JsonProperty("borrowedAt")
    @PastOrPresent(message = BORROWED_TIME_IN_FUTURE_MESSAGE)
    private LocalDateTime borrowedAt;

    @JsonProperty("returnAt")
    private LocalDateTime returnAt;
}
