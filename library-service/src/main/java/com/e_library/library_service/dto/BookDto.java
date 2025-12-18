package com.e_library.library_service.dto;

public record BookDto(BookIdDto id,
                      String title,
                      Integer bookYear,
                      String author,
                      String pressName) {
}
