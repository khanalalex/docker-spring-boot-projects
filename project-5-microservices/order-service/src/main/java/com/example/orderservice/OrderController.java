package com.example.orderservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderRepository repository;
    private final BookClient bookClient;

    public OrderController(OrderRepository repository, BookClient bookClient) {
        this.repository = repository;
        this.bookClient = bookClient;
    }

    @GetMapping
    public List<Order> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Order create(@RequestBody Map<String, Object> body) {
        Long bookId = Long.valueOf(body.get("bookId").toString());
        int quantity = Integer.parseInt(body.get("quantity").toString());

        BookClient.BookDto book = bookClient.getBook(bookId);
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book with id " + bookId + " does not exist");
        }

        Order order = new Order(book.id(), book.title(), quantity);
        return repository.save(order);
    }
}