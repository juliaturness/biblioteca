package ifsc.julia.backend.controllers;

import ifsc.julia.backend.dtos.LibraryRequestDTO;
import ifsc.julia.backend.models.Book;
import ifsc.julia.backend.models.UserBook;
import ifsc.julia.backend.services.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    private LibraryService libraryService;

    @PostMapping
    public ResponseEntity<Book> addBookToLibrary (@RequestBody LibraryRequestDTO request) {
        libraryService.addBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserBook>> getUserLibrary(@PathVariable UUID userId) {
        List<UserBook> userBooks = libraryService.getUserLibrary(userId);
        return ResponseEntity.ok(userBooks);

    }

}
