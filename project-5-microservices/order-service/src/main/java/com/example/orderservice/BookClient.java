package com.example.orderservice;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class BookClient {
    private final RestClient restClient = RestClient.create("http://book-service:8080");

    public BookDto getBook(Long bookId) {
        try {
            return restClient.get()
                    .uri("/api/books/{id}", bookId)
                    .retrieve()
                    .body(BookDto.class);
        } catch (Exception e) {
            return null;
        }
    }

    public record BookDto(Long id, String title, String author) {}
}