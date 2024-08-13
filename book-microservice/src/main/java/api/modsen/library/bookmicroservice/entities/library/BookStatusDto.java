package api.modsen.library.bookmicroservice.entities.library;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static api.modsen.library.bookmicroservice.config.LibraryAppConstants.*;

@Getter
@Setter
public class BookStatusDto {
    @JsonProperty("borrowedAt")
    @PastOrPresent(message = BORROWED_TIME_IN_FUTURE_MESSAGE)
    @NotNull(message = NULL_TIME_MESSAGE)
    private LocalDateTime borrowedAt;

    @JsonProperty("returnAt")
    @NotNull(message = NULL_TIME_MESSAGE)
    private LocalDateTime returnAt;

    @AssertTrue(message = RETURN_TIME_AFTER_BORROWED_TIME_MESSAGE)
    public boolean isReturnTimeAtLeastOneDayAfterBorrowedTime() {
        if (borrowedAt == null || returnAt == null) {
            return true;
        }
        return this.returnAt.isAfter(this.borrowedAt.plusDays(MIN_NUMBER_OF_DAYS_FOR_BORROWING));
    }
}