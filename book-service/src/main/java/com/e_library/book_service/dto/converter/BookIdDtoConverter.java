package com.e_library.book_service.dto.converter;

import com.e_library.book_service.dto.BookIdDto;
import com.e_library.book_service.model.Book;

public class BookIdDtoConverter {

    public static BookIdDto convert(Book from) {
        return new BookIdDto(
                from.getId(),
                from.getIsbn()
        );
    }
}
