package ifsc.julia.backend.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String title;
    private String author;
    private String genre;
    private String coverUrl;

    @Column(length = 2000)
    private String synopsis;
    private Integer totalPages;
    private String isbn;
    private LocalDateTime createdAt;
    private String metaDataStatus;

}
