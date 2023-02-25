package com.example.booksfromotherproject;

import com.example.booksfromotherproject.model.BookDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Main implements CommandLineRunner {

    private final RestTemplate restTemplate;

    public Main(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        ResponseEntity<BookDTO[]> allBooksResponse =
                restTemplate.getForEntity("http://localhost:8080/api/books", BookDTO[].class);

        if (allBooksResponse.hasBody()) {
            for (BookDTO book : allBooksResponse.getBody()) {
                System.out.println("Book: " + book);
            }
        }
    }
}
