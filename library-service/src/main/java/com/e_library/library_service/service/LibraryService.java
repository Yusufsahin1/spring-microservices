package com.e_library.library_service.service;

import com.e_library.library_service.dto.LibraryDto;
import com.e_library.library_service.exception.LibraryNotFoundException;
import com.e_library.library_service.model.Library;
import com.e_library.library_service.repository.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public LibraryDto getAllBooksInLibraryById(UUID id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new LibraryNotFoundException("Library could not found by id: " + id));

        return new LibraryDto(library.getId());
    }

    public LibraryDto createLibrary() {
        Library newLibrary = libraryRepository.save(new Library());
        return new LibraryDto(newLibrary.getId());
    }

}
