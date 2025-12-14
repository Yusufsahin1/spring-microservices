package com.e_library.library_service.dto;

import java.util.UUID;

public record AddBookRequest(
        UUID id,
        String isbn) {
}
