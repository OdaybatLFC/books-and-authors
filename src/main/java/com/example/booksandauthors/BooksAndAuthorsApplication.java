package com.example.booksandauthors;

import com.example.booksandauthors.services.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BooksAndAuthorsApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(BooksAndAuthorsApplication.class, args);
    }

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Override
    public void run(String... args) throws Exception {
        if (appUserDetailsService.isEmpty())
            appUserDetailsService.loadInitialData();
    }
}
