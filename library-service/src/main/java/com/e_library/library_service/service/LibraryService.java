package com.e_library.library_service.service;

import com.e_library.library_service.client.BookServiceClient;
import com.e_library.library_service.dto.AddBookRequest;
import com.e_library.library_service.dto.LibraryDto;
import com.e_library.library_service.exception.LibraryNotFoundException;
import com.e_library.library_service.model.Library;
import com.e_library.library_service.repository.LibraryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;
    private final BookServiceClient bookServiceClient;


    public LibraryService(LibraryRepository libraryRepository, BookServiceClient bookServiceClient) {
        this.libraryRepository = libraryRepository;
        this.bookServiceClient = bookServiceClient;
    }

    public LibraryDto getAllBooksInLibraryById(UUID id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new LibraryNotFoundException("Library could not found by id: " + id));

        return new LibraryDto(library.getId(),
                library.getUserBook()
                        .stream()
                        .map(bookServiceClient::findBookDetailsById)
                        .map(ResponseEntity::getBody)
                        .collect(Collectors.toList()));
    }

    public LibraryDto createLibrary() {
        Library newLibrary = libraryRepository.save(new Library());
        return new LibraryDto(newLibrary.getId());
    }

    public void addBookToLibrary(AddBookRequest request) {
        UUID bookId = bookServiceClient.findByIsbn(request.isbn()).getBody().bookId();

        Library library = libraryRepository.findById(request.id())
                .orElseThrow(() -> new LibraryNotFoundException("Library could not found by id: " + request.id()));

        library.getUserBook().add(bookId);
        libraryRepository.save(library);
    }

}
