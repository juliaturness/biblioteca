package ifsc.julia.backend.controllers;

import ifsc.julia.backend.models.Book;
import ifsc.julia.backend.repositories.BookRepository;
import ifsc.julia.backend.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/books")
public class BookController {


    private BookRepository bookRepository;
    private BookService bookService;

    @GetMapping("/")
    public ResponseEntity<Page<Book>> getCatalog(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Page<Book> catalog = bookService.getCatalog(page, size);
        return ResponseEntity.ok(catalog);
    }
    @GetMapping("/search")
    public ResponseEntity<String> searchExternalBooks(@RequestParam String query) {
        String result = bookService.searchExternalBooks(query);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search/{googleBookId}")
    public ResponseEntity<String> getExternalBookDetails(@PathVariable String googleBookId) {
        String bookDetails = bookService.getExternalBookDetails(googleBookId);
        return ResponseEntity.ok(bookDetails);
    }
}
