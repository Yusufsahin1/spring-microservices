package com.e_library.book_service.dto;

import java.util.UUID;

public record BookIdDto(
        UUID id,
        String isbn
) {
}
