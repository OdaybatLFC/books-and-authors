package com.example.booksandauthors.repositories;

import com.example.booksandauthors.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository  extends JpaRepository<Book, Long> {
}
