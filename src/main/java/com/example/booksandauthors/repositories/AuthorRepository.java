package com.example.booksandauthors.repositories;

import com.example.booksandauthors.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository  extends JpaRepository<Author, Long> {

}
