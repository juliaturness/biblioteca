package ifsc.julia.backend.services;

import ifsc.julia.backend.dtos.LibraryRequestDTO;
import ifsc.julia.backend.models.Book;
import ifsc.julia.backend.models.ReadingStatus;
import ifsc.julia.backend.models.User;
import ifsc.julia.backend.models.UserBook;
import ifsc.julia.backend.repositories.BookRepository;
import ifsc.julia.backend.repositories.UserBookRepository;
import ifsc.julia.backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LibraryService {

    private BookRepository bookRepository;
    private UserRepository userRepository;
    private UserBookRepository userBookRepository;

    public LibraryService(BookRepository bookRepository,
                          UserRepository userRepository,
                          UserBookRepository userBookRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.userBookRepository = userBookRepository;
    }

    public void addBook(LibraryRequestDTO request){
        Optional<Book> existingBook = bookRepository.findByIsbn(request.getIsbn());
        Book book;
        if(existingBook.isPresent()){
            book = existingBook.get();
        }  else {
            book = new Book();
            book.setIsbn(request.getIsbn());
            book.setTitle(request.getTitle());
            book.setAuthor(request.getAuthor());
            book.setDescription(request.getDescription());
            book.setCoverUrl(request.getCoverUrl());
            book.setTotalPages(request.getTotalPages());
            book.setAverageRating(request.getAverageRating());
            bookRepository.save(book);
        }
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        UserBook userBook = new UserBook();
        userBook.setUserId(user);
        userBook.setBookId(book);
        userBook.setStatus(request.getStatus());
        userBook.setCurrentPage(0);
        userBook.setStartDate(LocalDateTime.now());
        userBookRepository.save(userBook);
    }

    public List<UserBook> getUserLibrary(UUID userId) {
        return userBookRepository.findByUserId_Id(userId);
    }

    public void updateReadingStatus(Long userBookId, ReadingStatus newStatus) {
        UserBook userBook = userBookRepository.findById(userBookId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado na estante!"));

        if (newStatus == ReadingStatus.READING) {
            if (userBook.getStartDate() == null) {
                userBook.setStartDate(LocalDateTime.now());
            }
        } else if (newStatus == ReadingStatus.READ) {
            userBook.setFinishDate(LocalDateTime.now());
            if (userBook.getStartDate() == null) {
                userBook.setStartDate(LocalDateTime.now());
            }
        }
        userBook.setStatus(newStatus);
        userBookRepository.save(userBook);
    }

}
