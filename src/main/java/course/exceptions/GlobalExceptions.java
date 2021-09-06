package course.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import course.exceptions.UserExceptions.UserNotCreatedException;
import course.exceptions.UserExceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@ControllerAdvice
public class GlobalExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleAnyExceptions(Exception exception, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(),
                request.getDescription(true));
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { UserNotFoundException.class })
    public ResponseEntity<Object> handle404NotFoundExceptions(Exception exception, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(),
                request.getDescription(true));
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { UserNotCreatedException.class })
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(),
                exception.getBindingResult().toString());
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @AllArgsConstructor
    @Getter
    @Setter
    private class ExceptionResponse {
        private Date timestamp;
        private String message;
        private String details;
    }
}
