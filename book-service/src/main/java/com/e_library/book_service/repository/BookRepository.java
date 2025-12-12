package com.e_library.book_service.repository;

import com.e_library.book_service.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID>{

    Optional<Book> getBookByIsbn(String isbn);
}
