package com.example.booksandauthors.controllers;

import com.example.booksandauthors.assemblers.BookModelAssembler;
import com.example.booksandauthors.entities.Book;
import com.example.booksandauthors.models.BookModel;
import com.example.booksandauthors.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookModelAssembler bookModelAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<BookModel>> getBooks() {
        List<Book> books = bookService.getBooks();
        return ResponseEntity.ok(bookModelAssembler.toCollectionModel(books));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookModel> getBookById(@PathVariable("id") Long id) {
        Book book = bookService.findBook(id);
        return ResponseEntity.ok(bookModelAssembler.toModel(book));
    }

    @GetMapping("/{id}/authors")
    public ResponseEntity<?> getAuthorsByBook(@PathVariable("id") Long id) {
        BookModel bookModel = bookModelAssembler.toModel(bookService.findBook(id));
        return ResponseEntity.ok(bookModel.getAuthors());
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book) {

        BookModel entityModel = bookModelAssembler.toModel(bookService.save(book));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

}
