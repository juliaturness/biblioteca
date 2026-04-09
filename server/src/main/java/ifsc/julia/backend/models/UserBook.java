package ifsc.julia.backend.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_books")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book bookId;

    @Enumerated(EnumType.STRING)
    private ReadingStatus status;

    private LocalDateTime startDate;
    private LocalDateTime finishDate;

    private Integer currentPage;
    private Integer rating;
    private String review;

}
