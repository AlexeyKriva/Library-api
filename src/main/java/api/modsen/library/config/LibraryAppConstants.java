package api.modsen.library.config;

public class LibraryAppConstants {
    public static final String ISBN_FORMAT = "^(?:978|979)-\\d{1,5}-\\d{1,7}-\\d{1,7}-\\d{1}$";
    public static final String INVALID_ISBN_MESSAGE = "Invalid isbn format";
    public static final String BLANK_ISBN_MESSAGE = "Isbn cannot be blank";
    public static final String BLANK_TITLE_MESSAGE = "Title cannot be blank";
    public static final String BLANK_GENRE_MESSAGE = "Genre cannot be blank";
    public static final String BLANK_DESCRIPTION_MESSAGE = "Description cannot be blank";
    public static final String BLANK_AUTHOR_MESSAGE = "Author cannot be blank";
    public static final short MAX_LENGTH_OF_BOOK_TITLE = 50;
    public static final short MAX_LENGTH_OF_BOOK_DESCRIPTION = 1000;
    public static final short MAX_LENGTH_OF_BOOK_GENRE_AND_AUTHOR = 255;
    public static final String BOOK_NOT_FOUND_MESSAGE_WITH_ID = "Book not found with id:";
    public static final String BOOK_NOT_FOUND_MESSAGE_WITH_ISBN = "Book not found with isbn:";

}
