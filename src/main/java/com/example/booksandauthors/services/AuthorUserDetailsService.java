package com.example.booksandauthors.services;

import com.example.booksandauthors.config.AuthorUserDetails;
import com.example.booksandauthors.entities.Author;
import com.example.booksandauthors.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
public class AuthorUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Author user = authorRepository.getByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        return new AuthorUserDetails(user);
    }
}
