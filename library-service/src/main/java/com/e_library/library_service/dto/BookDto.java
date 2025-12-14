package com.e_library.library_service.dto;

public record BookDto(BookIdDto id,
                      String title,
                      Integer year,
                      String author,
                      String pressName) {
}
