package ifsc.julia.backend.dtos;

import ifsc.julia.backend.models.ReadingStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class LibraryRequestDTO {
    private String isbn;
    private String title;
    private String author;
    private String description;
    private String coverUrl;
    private Integer totalPages;
    private double averageRating;
    private ReadingStatus status;
    private UUID userId;
}