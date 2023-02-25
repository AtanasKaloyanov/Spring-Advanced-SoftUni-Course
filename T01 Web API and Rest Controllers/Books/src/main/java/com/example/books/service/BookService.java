package com.example.books.service;

import com.example.books.model.dto.AuthorDTO;
import com.example.books.model.dto.BookDTO;
import com.example.books.model.entity.BookEntity;
import com.example.books.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void deleteBookById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public Optional<BookDTO> getBookById(Long bookId) {
        return bookRepository
                .findById(bookId)
                .map(this::mapFromBookToBookDTO);
    }

    private BookDTO mapFromBookToBookDTO(BookEntity book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setIsbn(book.getIsbn());

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName(book.getAuthor().getName());
        bookDTO.setAuthorDTO(authorDTO);

        return bookDTO;
    }

    public List<BookDTO> getAllBooks() {
       return bookRepository.findAll()
                .stream()
                .map(this::mapFromBookToBookDTO)
                .collect(Collectors.toList());
    }

    public Long createBook(BookDTO bookDTO) {
        // TODO:

        return 50L;
    }
}
