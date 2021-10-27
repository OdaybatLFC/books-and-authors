package com.example.booksandauthors.repositories;

import com.example.booksandauthors.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
