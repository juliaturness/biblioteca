package ifsc.julia.backend.dtos;

import ifsc.julia.backend.models.ReadingStatus;

public class LibraryRequestDTO {
    private String isbn;
    private String title;
    private String author;
    private String description;
    private String coverUrl;
    private Integer totalPages;
    private double averageRating;

    private ReadingStatus status;
}