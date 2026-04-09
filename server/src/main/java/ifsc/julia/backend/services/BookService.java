package ifsc.julia.backend.services;

import ifsc.julia.backend.models.Book;
import ifsc.julia.backend.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


@Service
public class BookService {

    private final BookRepository bookRepository;
    private final GoogleBooksService googleBooksService;
    public BookService(BookRepository bookRepository,  GoogleBooksService googleBooksService) {
        this.bookRepository = bookRepository;
        this.googleBooksService = googleBooksService;
    }

    public Page<Book> getCatalog(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable);
    }

    public String searchExternalBooks (String query) {
        return googleBooksService.searchBooks(query);
    }

    public String getExternalBookDetails(String googleBookId) {
        return googleBooksService.getBookDetails(googleBookId);
    }
}
