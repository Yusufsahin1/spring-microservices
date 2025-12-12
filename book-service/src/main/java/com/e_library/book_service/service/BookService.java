package com.e_library.book_service.service;

import com.e_library.book_service.dto.BookDto;
import com.e_library.book_service.dto.BookIdDto;
import com.e_library.book_service.dto.converter.BookDtoConverter;
import com.e_library.book_service.exception.BookNotFoundException;
import com.e_library.book_service.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookDtoConverter::convert)
                .collect(Collectors.toList());
    }

    public BookIdDto findByIsbn(String isbn) {
        return bookRepository.getBookByIsbn(isbn)
                .map(book -> new BookIdDto(book.getId(), book.getIsbn()))
                .orElseThrow(() -> new BookNotFoundException("Book could not found by isbn: " + isbn));
    }

    public BookDto findBookDetailsById(UUID id){
        return bookRepository.findById(id)
                .map(BookDtoConverter::convert)
                .orElseThrow(() -> new BookNotFoundException("Book could not found by id: " + id));
    }

}
