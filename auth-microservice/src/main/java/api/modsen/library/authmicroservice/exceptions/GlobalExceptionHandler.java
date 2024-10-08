package api.modsen.library.authmicroservice.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.security.auth.message.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

;import static api.modsen.library.authmicroservice.config.AuthAppConstants.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ResponseEntity<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException
                                                                   missingParameterException) {
        String error = String.format(MISSING_PARAMETERS_MESSAGE + "%s", missingParameterException.getParameterName());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MultiplyMissingParametersException.class)
    @ResponseBody
    public ResponseEntity<String> handleMultiplyMissingParametersException(MultiplyMissingParametersException
                                                                           multiplyMissingParametersException) {
        String error = String.format(MISSING_PARAMETERS_MESSAGE + "%s", String.join(", ",
                multiplyMissingParametersException.getMissingParameters() ));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException
                                                              argumentTypeMismatchException) {
        String error = String.format(INVALID_FORMAT_FOR_PARAMETER_MESSAGE + "%s",
                argumentTypeMismatchException.getName());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException
                                                                    messageNotReadableException) {
        return new ResponseEntity<>(DATA_CANNOT_BE_EXTRACT, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException
                                                                    methodNotSupportedException) {
        String error = METHOD_NOT_ALLOWED_MESSAGE + methodNotSupportedException.getSupportedHttpMethods();
        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleNoHandlerFoundException(NoHandlerFoundException noHandlerFoundException) {
        return new ResponseEntity<>(NOTHING_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException
                                                                        argumentNotValidException) {
        Map<String, String> errors = new HashMap<>();
        argumentNotValidException.getAllErrors().forEach((error) -> {
                    String field = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(field, message);
                }
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    @ResponseBody
    public ResponseEntity<List<String>> handleHandlerMethodValidationException(HandlerMethodValidationException
                                                                                             handlerMethodValidationException) {
        List<String> errors = new ArrayList<>();
        handlerMethodValidationException.getAllErrors().forEach((error) -> {
                    String message = error.getDefaultMessage();
                    errors.add(message);
                }
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> handleExpiredJwtException(ExpiredJwtException expiredJwtException) {
        return new ResponseEntity<>(TOKEN_EXPIRED_MESSAGE, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> handleUnsupportedJwtException(UnsupportedJwtException unsupportedJwtException) {
        return new ResponseEntity<>(UNSUPPORTED_JWT_TOKEN_MESSAGE, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MalformedJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> handleMalformedJwtException(MalformedJwtException malformedJwtException) {
        return new ResponseEntity<>(MALFORMED_JWT_TOKEN_MESSAGE, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> handleSignatureException(SignatureException signatureException) {
        return new ResponseEntity<>(INVALID_JWT_SIGNATURE, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException authenticationException) {
        return new ResponseEntity<>(authenticationException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException accessDeniedException) {
        return new ResponseEntity<>(DENIED_ACCESS_MESSAGE, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthException.class)
    @ResponseBody
    public ResponseEntity<String> handleAuthException(AuthException authException) {
        return new ResponseEntity<>(authException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}