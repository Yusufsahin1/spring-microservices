package com.e_library.library_service.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record LibraryDto(
        UUID id,
        List<BookDto> userBookList)
{
    public LibraryDto {
        if (userBookList == null) {
            userBookList = new ArrayList<>();
        }
    }

    public LibraryDto(UUID id) {
        this(id, new ArrayList<>());
    }
}
