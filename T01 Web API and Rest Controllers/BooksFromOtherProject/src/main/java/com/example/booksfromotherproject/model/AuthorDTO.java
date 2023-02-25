package com.example.booksfromotherproject.model;

public class AuthorDTO {
    private String name;

    public AuthorDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
