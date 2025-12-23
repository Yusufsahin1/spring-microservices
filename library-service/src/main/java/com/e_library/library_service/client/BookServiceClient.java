package com.e_library.library_service.client;

import com.e_library.library_service.dto.BookDto;
import com.e_library.library_service.dto.BookIdDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "book-service", path = "/api/v1/book")
public interface BookServiceClient {

    Logger logger = LoggerFactory.getLogger(BookServiceClient.class);

    @GetMapping("/isbn/{isbn}")
    @CircuitBreaker(name = "findBookByIsbnCircuitBreaker", fallbackMethod = "getBookFallback")
    ResponseEntity<BookIdDto> findByIsbn(@PathVariable String isbn);

    default ResponseEntity<BookIdDto> getBookFallback(String isbn, Exception exception) {
        logger.info("Book not found by isbn " + isbn + ", returning default BookDto object");
        return ResponseEntity.ok(new BookIdDto(UUID.randomUUID(), isbn));
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "findBookByIdCircuitBreaker", fallbackMethod = "getBookByIdFallback")
    ResponseEntity<BookDto> findBookDetailsById(@PathVariable UUID id);

    default ResponseEntity<BookDto> getBookByIdFallback(UUID id, Exception exception) {
        logger.info("Book not found by id " + id + ", returning default BookDto object");
        return ResponseEntity.ok(new BookDto(
                new BookIdDto(id, "default-isbn"),
                "default-title",
                0,
                "default-author",
                "default-press"
        ));
    }
}
