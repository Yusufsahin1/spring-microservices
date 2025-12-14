package com.e_library.library_service.repository;

import com.e_library.library_service.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LibraryRepository extends JpaRepository<Library, UUID> {
}
