package com.e_library.library_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {

    private ExceptionMessage exceptionMessage;
    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(ExceptionMessage message) {
        this.exceptionMessage = message;
    }

    public BookNotFoundException(String message, ExceptionMessage exceptionMessage) {
        super(message);
        this.exceptionMessage = exceptionMessage;
    }

}
