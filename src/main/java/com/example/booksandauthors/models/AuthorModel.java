package com.example.booksandauthors.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Relation(itemRelation = "author", collectionRelation = "authors")
public class AuthorModel extends RepresentationModel<AuthorModel> {

    private final UUID uuid = UUID.randomUUID();

    private String firstName;
    private String lastName;

    @JsonIgnore
    private Set<BookModel> books = new HashSet<>();

    public Set<BookModel> getBooks() {
        return books;
    }

    public void setBooks(Set<BookModel> books) {
        this.books = books;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

}
