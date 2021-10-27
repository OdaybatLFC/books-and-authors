package com.example.booksandauthors.services;

import com.example.booksandauthors.entities.Author;
import com.example.booksandauthors.entities.Book;
import com.example.booksandauthors.entities.User;
import com.example.booksandauthors.repositories.UserRepository;
import com.example.booksandauthors.security.AppUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUserDetails> user = getApplicationUsers().stream().
                filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
        if(user.isPresent()) return user.get(); else throw new UsernameNotFoundException("User not found!");
    }

    private List<AppUserDetails> getApplicationUsers() {
        List<AppUserDetails> users = new ArrayList<>();
        for(User user: userRepository.findAll()) {
            AppUserDetails appUser = new AppUserDetails(user);
            users.add(appUser);
        }
        return users;
    }

    public void loadInitialData() {
        User admin = new User("admin", passwordEncoder.encode("admin"), "ADMIN");

        User joanna = new User("jk", passwordEncoder.encode("jk123"), "AUTHOR");
        joanna.setAuthor(new Author("J.K.", "Rowling", joanna));
        joanna.getAuthor().getBooks().add(new Book("Harry Potter"));

        User arthur = new User("arthur", passwordEncoder.encode("arthur123"), "AUTHOR");
        arthur.setAuthor(new Author("Arthur", "Doyle", arthur));
        arthur.getAuthor().getBooks().add(new Book("Sherlock Holmes"));
        arthur.getAuthor().getBooks().add(new Book("The Napoleonic Tales"));

        Book sharedBook = new Book("Head First Java");
        User bert = new User("bert", passwordEncoder.encode("bert123"), "AUTHOR");
        bert.setAuthor(new Author("Bert", "Bates", bert));
        bert.getAuthor().getBooks().add(sharedBook);

        User kathy = new User("kathy", passwordEncoder.encode("kathy123"), "AUTHOR");
        kathy.setAuthor(new Author("Kathy", "Sierra", kathy));
        kathy.getAuthor().getBooks().add(sharedBook);
        kathy.getAuthor().getBooks().add(new Book("Head First Design Patterns"));

        userRepository.saveAll(Arrays.asList(joanna,admin,arthur,bert,kathy));
    }

    public boolean isEmpty() {
        return userRepository.findAll().size() == 0;
    }
}
