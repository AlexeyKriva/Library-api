package api.modsen.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

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
    public ResponseEntity<String> handleMissingParametersException(MissingServletRequestParameterException
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
    public ResponseEntity<String> handleArgumentsTypeMismatch(MethodArgumentTypeMismatchException
                                                              argumentTypeMismatchException) {
        String error = String.format(INVALID_FORMAT_FOR_PARAMETER_MESSAGE + "%s",
                argumentTypeMismatchException.getName());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<String> handleMessageNotReadableException(HttpMessageNotReadableException
                                                                    messageNotReadableException) {
        return new ResponseEntity<>(DATA_CANNOT_BE_EXTRACT, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<String> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException
                                                                    methodNotSupportedException) {
        String error = METHOD_NOT_ALLOWED_MESSAGE + methodNotSupportedException.getSupportedHttpMethods();
        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handleNotFound(NoHandlerFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOTHING_NOT_FOUND_MESSAGE);
    }
}