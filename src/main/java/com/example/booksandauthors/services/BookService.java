package com.example.booksandauthors.services;

import com.example.booksandauthors.entities.Book;
import com.example.booksandauthors.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book findBook(long id) {
        Optional<Book> author = bookRepository.findById(id);
        return author.orElse(null);
    }
}
