package com.e_library.library_service.client;

import com.e_library.library_service.dto.BookDto;
import com.e_library.library_service.dto.BookIdDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "book-service", path = "/api/v1/book")
public interface BookServiceClient {

    @GetMapping("/isbn/{isbn}")
    ResponseEntity<BookIdDto> findByIsbn(@PathVariable String isbn);

    @GetMapping("/{id}")
    ResponseEntity<BookDto> findBookDetailsById(@PathVariable UUID id);
}
