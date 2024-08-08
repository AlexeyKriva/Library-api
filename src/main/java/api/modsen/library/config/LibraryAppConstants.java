package api.modsen.library.config;

public class LibraryAppConstants {
    public static final String ISBN_FORMAT = "^(?:978|979)-\\d{1,5}-\\d{1,7}-\\d{1,7}-\\d{1}$";
    public static final String INVALID_ISBN_MESSAGE = "Invalid isbn format";
    public static final String BLANK_ISBN_MESSAGE = "Isbn cannot be blank";
    public static final String BLANK_TITLE_MESSAGE = "Title cannot be blank";
    public static final String BLANK_GENRE_MESSAGE = "Genre cannot be blank";
    public static final String BLANK_DESCRIPTION_MESSAGE = "Description cannot be blank";
    public static final String BLANK_AUTHOR_MESSAGE = "Author cannot be blank";
    public static final int MAX_LENGTH_OF_BOOK_TITLE = 50;
    public static final int MAX_LENGTH_OF_BOOK_DESCRIPTION = 1000;
    public static final int MAX_LENGTH_OF_BOOK_GENRE_AND_AUTHOR = 255;
    public static final String BOOK_NOT_FOUND_MESSAGE_WITH_ID = "Book not found with id: ";
    public static final String BOOK_NOT_FOUND_MESSAGE_WITH_ISBN = "Book not found with isbn: ";
    public static final int MAX_NUMBER_OF_DAYS_BORROW_BOOK = 30;
    public static final String BORROWED_TIME_IN_FUTURE_MESSAGE = "Borrowed time cannot be in the future";
    public static final String MISSING_PARAMETERS_MESSAGE = "Missing parameters: ";
    public static final String INVALID_FORMAT_FOR_PARAMETER_MESSAGE = "Invalid format for parameter: ";
    public static final String DATA_CANNOT_BE_EXTRACT = "Data cannot be extract";
    public static final String METHOD_NOT_ALLOWED_MESSAGE = "Method not allowed. Allowed methods are: ";
    public static final String NOTHING_NOT_FOUND_MESSAGE = "Nothing was found on the request";
}
