package com.example.booksandauthors.services;

import com.example.booksandauthors.entities.User;
import com.example.booksandauthors.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void update() {
        userRepository.flush();
    }

    public User findUser(long id)  {
        Optional<User> author = userRepository.findById(id);
        return author.orElse(null);
    }

    public User removeUser(long id) {
        User user = this.findUser(id);
        userRepository.delete(user);
        return user;
    }

}
