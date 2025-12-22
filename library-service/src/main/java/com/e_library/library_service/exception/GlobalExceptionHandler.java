package com.e_library.library_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LibraryNotFoundException.class)
    public ResponseEntity<?> handle(LibraryNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handle(BookNotFoundException exception) {
        ExceptionMessage message = exception.getExceptionMessage();
        HttpStatus status = HttpStatus.NOT_FOUND;

        if (message != null) {
            HttpStatus resolvedStatus = HttpStatus.resolve(message.status());
            if (resolvedStatus != null) {
                status = resolvedStatus;
            }
        }

        return new ResponseEntity<>(message, status);
    }
}
