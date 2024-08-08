package api.modsen.library.exceptions;

import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static api.modsen.library.config.LibraryAppConstants.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException bookNotFoundException) {
        return new ResponseEntity<>(bookNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

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
    public ResponseEntity<String> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return new ResponseEntity<>(NOTHING_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException
                                                                        dataIntegrityViolationException) {
        return new ResponseEntity<>(DATA_INTEGRITY_VIOLATION, HttpStatus.BAD_REQUEST);
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
}