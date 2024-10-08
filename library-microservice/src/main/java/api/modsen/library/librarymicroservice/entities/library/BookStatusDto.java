package api.modsen.library.librarymicroservice.entities.library;

import api.modsen.library.librarymicroservice.config.LibraryAppConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static api.modsen.library.librarymicroservice.config.LibraryAppConstants.*;

@Getter
@Setter
public class BookStatusDto {
    @JsonProperty("borrowedAt")
    @PastOrPresent(message = LibraryAppConstants.BORROWED_TIME_IN_FUTURE_MESSAGE)
    @NotNull(message = LibraryAppConstants.NULL_TIME_MESSAGE)
    private LocalDateTime borrowedAt;

    @JsonProperty("returnAt")
    @NotNull(message = LibraryAppConstants.NULL_TIME_MESSAGE)
    private LocalDateTime returnAt;

    @AssertTrue(message = LibraryAppConstants.RETURN_TIME_AFTER_BORROWED_TIME_MESSAGE)
    public boolean isReturnTimeAtLeastOneDayAfterBorrowedTime() {
        if (borrowedAt == null || returnAt == null) {
            return true;
        }
        return this.returnAt.isAfter(this.borrowedAt.plusDays(LibraryAppConstants.MIN_NUMBER_OF_DAYS_FOR_BORROWING));
    }

    public LocalDateTime getBorrowedAt() {
        return borrowedAt;
    }

    public LocalDateTime getReturnAt() {
        return returnAt;
    }

    public void setBorrowedAt(LocalDateTime borrowedAt) {
        this.borrowedAt = borrowedAt;
    }

    public void setReturnAt(LocalDateTime returnAt) {
        this.returnAt = returnAt;
    }
}