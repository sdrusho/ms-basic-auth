package org.basic.micorservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Set the HTTP status code
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(RuntimeException ex) {
        ErrorResponse exc = new ErrorResponse(ex.getMessage(),HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(exc, HttpStatus.BAD_REQUEST);
    }
}
