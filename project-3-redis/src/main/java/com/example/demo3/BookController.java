package com.example.demo3;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Cacheable("books")
    public List<Book> getAll() {
        System.out.println(">>> Hitting the DATABASE (not cache)");
        return repository.findAll();
    }

    @PostMapping
    @CacheEvict(value = "books", allEntries = true)
    public Book create(@RequestBody Book book) {
        return repository.save(book);
    }
}