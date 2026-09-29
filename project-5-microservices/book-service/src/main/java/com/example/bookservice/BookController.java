package com.example.bookservice;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Book> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Book> getById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        return repository.save(book);
    }
}