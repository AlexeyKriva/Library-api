package api.modsen.library.config;

public class LibraryAppConstants {
    public static final String ISBN_FORMAT = "^(?:978|979)-\\d{1,5}-\\d{1,7}-\\d{1,7}-\\d{1}$";
    public static final String INVALID_ISBN_MESSAGE = "Invalid isbn format.";
    public static final String BLANK_ISBN_MESSAGE = "Isbn cannot be blank or validation error.";
    public static final String BLANK_TITLE_MESSAGE = "Title cannot be blank or validation error.";
    public static final String BLANK_GENRE_MESSAGE = "Genre cannot be blank or validation error.";
    public static final String BLANK_DESCRIPTION_MESSAGE = "Description cannot be blank or validation error.";
    public static final String BLANK_AUTHOR_MESSAGE = "Author cannot be blank or validation error.";
    public static final int MAX_LENGTH_OF_BOOK_TITLE = 50;
    public static final int MAX_LENGTH_OF_BOOK_DESCRIPTION = 1000;
    public static final int MAX_LENGTH_OF_BOOK_GENRE_AND_AUTHOR = 255;
    public static final String BOOK_NOT_FOUND_MESSAGE_WITH_ID = "Book not found with id: ";
    public static final String BOOK_NOT_FOUND_MESSAGE_WITH_ISBN = "Book not found with isbn: ";
    public static final int MAX_NUMBER_OF_DAYS_BORROW_BOOK = 30;
    public static final String BORROWED_TIME_IN_FUTURE_MESSAGE = "Borrowed time cannot be in the future.";
    public static final String MISSING_PARAMETERS_MESSAGE = "Missing parameters: ";
    public static final String INVALID_FORMAT_FOR_PARAMETER_MESSAGE = "Invalid format for parameter: ";
    public static final String DATA_CANNOT_BE_EXTRACT = "Data cannot be extract.";
    public static final String METHOD_NOT_ALLOWED_MESSAGE = "Method not allowed. Allowed methods are: ";
    public static final String NOTHING_NOT_FOUND_MESSAGE = "Nothing was found on the request.";
    public static final String DATA_INTEGRITY_VIOLATION = "An attempt to write uncorrected data to the database. " +
            "Possible reasons: a book with such an isbn already exists, " +
            "some data is not filled in, or you are referring to non-existent data.";
    public static final int MIN_NUMBER_OF_DAYS_FOR_BORROWING = 1;
    public static final String RETURN_TIME_AFTER_BORROWED_TIME_MESSAGE = "The return time must be at least 1 day after the borrowing time.";
    public static final String NULL_TIME_MESSAGE = "Time cannot be null.";
    public static final String TOKEN_EXPIRED_MESSAGE = "Token expired.";
    public static final String UNSUPPORTED_JWT_TOKEN_MESSAGE = "Unsupported JWT token.";
    public static final String MALFORMED_JWT_TOKEN_MESSAGE = "Malformed JWT token.";
    public static final String INVALID_JWT_SIGNATURE = "Invalid JWT signature.";
    public static final String USER_NOT_FOUND_MESSAGE = "User not found.";
    public static final String INVALID_ACCESS_TOKEN_MESSAGE = "Token invalid.";
    public static final String BLANK_USERNAME_MESSAGE = "Username cannot be blank.";
    public static final String BLANK_PASSWORD_MESSAGE = "Password cannot be blank.";
    public static final String BLANK_REFRESH_TOKEN_MESSAGE = "Refresh token cannot be blank.";
    public static final String DENIED_ACCESS_MESSAGE = "No rights to access resource";
}
