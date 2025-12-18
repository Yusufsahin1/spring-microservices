package com.e_library.book_service.dto;

public record BookDto(
        BookIdDto id,
        String title,
        Integer bookYear,
        String author,
        String pressName
) {
}
