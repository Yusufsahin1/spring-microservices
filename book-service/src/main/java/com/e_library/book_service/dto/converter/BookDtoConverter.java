package com.e_library.book_service.dto.converter;


import com.e_library.book_service.dto.BookDto;
import com.e_library.book_service.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookDtoConverter {

    public static BookDto convert(Book from) {
        return new BookDto(
                BookIdDtoConverter.convert(from),
                from.getTitle(),
                from.getBookYear(),
                from.getAuthor(),
                from.getPressName()
        );
    }
}
