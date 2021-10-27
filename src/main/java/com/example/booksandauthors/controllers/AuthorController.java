package com.example.booksandauthors.controllers;

import com.example.booksandauthors.entities.Author;
import com.example.booksandauthors.entities.Book;
import com.example.booksandauthors.models.AuthorModel;
import com.example.booksandauthors.assemblers.AuthorModelAssembler;
import com.example.booksandauthors.security.AppUserDetails;
import com.example.booksandauthors.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.*;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorModelAssembler authorModelAssembler;

    @GetMapping
    @PreAuthorize("hasAuthority('author:read')")
    public ResponseEntity<CollectionModel<AuthorModel>> getAuthors() {
        List<Author> authors = authorService.getAuthors();
        return ResponseEntity.ok(authorModelAssembler.toCollectionModel(authors));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable("id")  Long id, Authentication authentication) {
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        if(Objects.equals(userDetails.getUser().getId(), id))
            return ResponseEntity.ok(authorModelAssembler.toModel(authorService.findAuthor(id)));
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<?> getBooksByAuthor(@PathVariable("id")  Long id) {
        return ResponseEntity.ok(authorModelAssembler.toModel(authorService.findAuthor(id)).getBooks());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('author:add')")
    public ResponseEntity<?> addAuthor(@RequestBody Author author) {
        AuthorModel entityModel = authorModelAssembler.toModel(authorService.save(author));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('author:update')")
    public ResponseEntity<?> updateAuthorName(@PathVariable long id, @RequestBody Map<Object, Object> fields) {
        Author author = authorService.findAuthor(id);
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Author.class, (String) key);
            if(field == null) throw new NullPointerException(); else field.setAccessible(true);
            ReflectionUtils.setField(field, author, value);
        });
        authorService.update();
        return ResponseEntity.ok(authorModelAssembler.toModel(author));
    }

    @PatchMapping("/{id}/books")
    public ResponseEntity<?> addBooks(@PathVariable long id, @RequestBody Map<Object, Set<Book>>fields) {
        Author author = authorService.findAuthor(id);
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Author.class, (String) key);
            if(field == null) throw new NullPointerException(); else field.setAccessible(true);
            value.forEach(book -> author.getBooks().add(book));
        });
        authorService.update();
        return ResponseEntity.ok(authorModelAssembler.toModel(author).getBooks());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('author:remove')")
    public ResponseEntity<?> removeAuthor(@PathVariable long id) {
        return ResponseEntity.ok(authorModelAssembler.toModel(authorService.removeAuthor(id)));
    }

}