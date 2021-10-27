package com.example.booksandauthors.services;

import com.example.booksandauthors.entities.Author;
import com.example.booksandauthors.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public void update() {
        authorRepository.flush();
    }

    public Author findAuthor(long id)  {
        Optional<Author> author = authorRepository.findById(id);
        return author.orElse(null);
    }

    public Author removeAuthor(long id) {
        Author author = this.findAuthor(id);
        authorRepository.delete(author);
        return author;
    }
}
