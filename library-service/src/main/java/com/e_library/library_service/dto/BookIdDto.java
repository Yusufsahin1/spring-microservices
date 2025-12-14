package com.e_library.library_service.dto;

import java.util.UUID;

public record BookIdDto(
        UUID bookId,
        String isbn) {
}
