package com.e_library.book_service.dto;

import java.util.UUID;

public record BookDto(
        UUID id,
        String title,
        Integer bookYear,
        String author,
        String pressName
) {
}
