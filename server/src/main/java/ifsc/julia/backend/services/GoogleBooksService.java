package ifsc.julia.backend.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class GoogleBooksService {

    @Value("${google.books.api.key}")
    private String apiKey;

    private final RestClient restClient;

    public GoogleBooksService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl("https://www.googleapis.com/books/v1").build();
    }

    public String searchBooks(String query) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/volumes")
                        .queryParam("q", query)
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .body(String.class);
    }
    public String getBookDetails(String googleBookId) {
        return restClient.get()
                .uri("/volumes/{id}", googleBookId)
                .retrieve()
                .body(String.class);
    }
}