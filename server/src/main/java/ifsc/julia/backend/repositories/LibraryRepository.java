package ifsc.julia.backend.repositories;

import ifsc.julia.backend.models.Book;
import ifsc.julia.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Book, User> {
}
