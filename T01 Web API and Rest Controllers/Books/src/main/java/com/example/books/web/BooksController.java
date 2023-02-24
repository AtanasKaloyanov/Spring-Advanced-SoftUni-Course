package com.example.books.web;

import com.example.books.model.dto.BookDTO;
import com.example.books.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/books")
@RestController
public class BooksController {
    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity
                .ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookByID(@PathVariable("id") Long bookId) {
        Optional<BookDTO> boookOpt = bookService.getBookById(bookId);

        if (boookOpt.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

       return ResponseEntity
               .ok(boookOpt.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBookById(@PathVariable("id") Long bookId) {
        bookService.deleteBookById(bookId);

        return ResponseEntity.noContent().build();
    }
}
