package com.e_library.library_service.contoller;

import com.e_library.library_service.dto.LibraryDto;
import com.e_library.library_service.service.LibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/library")
public class LibraryController {

    private final LibraryService libraryService;
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("{id}")
    public ResponseEntity<LibraryDto> getAllBooksInLibraryById(@PathVariable UUID id) {
        return  ResponseEntity.ok(libraryService.getAllBooksInLibraryById(id));
    }

    @PostMapping
    public ResponseEntity<LibraryDto> createLibrary() {
        return ResponseEntity.ok(libraryService.createLibrary());
    }
}
