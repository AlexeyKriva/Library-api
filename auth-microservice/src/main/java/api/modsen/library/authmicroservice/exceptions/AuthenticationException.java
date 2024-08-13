package api.modsen.library.authmicroservice.exceptions;

public class AuthenticationException extends RuntimeException{
    public AuthenticationException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
