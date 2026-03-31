package ifsc.julia.backend.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    private String title;
    private String author;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String genre;
    private String coverUrl;
    private Integer totalPages;
    @Column(unique = true)
    private String isbn;
    private double averageRating;
}
