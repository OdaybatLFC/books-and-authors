package com.example.booksandauthors.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BookModel extends RepresentationModel<BookModel> {

    private final UUID uuid = UUID.randomUUID();

    private String name;

    @JsonIgnore
    private Set<AuthorModel> authors = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AuthorModel> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorModel> authors) {
        this.authors = authors;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
}
