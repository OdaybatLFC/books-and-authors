package com.example.booksandauthors.assemblers;

import com.example.booksandauthors.controllers.AuthorController;
import com.example.booksandauthors.entities.Author;
import com.example.booksandauthors.entities.Book;
import com.example.booksandauthors.models.AuthorModel;
import com.example.booksandauthors.models.BookModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class AuthorModelAssembler extends RepresentationModelAssemblerSupport<Author, AuthorModel> {

    public AuthorModelAssembler() {
        super(AuthorController.class, AuthorModel.class);
    }

    @Override
    public AuthorModel toModel(Author entity) {
        AuthorModel authorModel = instantiateModel(entity);
        authorModel.setFirstName(entity.getFirstName());
        authorModel.setLastName(entity.getLastName());
        authorModel.setBooks(toBookModel(entity.getBooks()));

        return authorModel;
    }

    private Set<BookModel> toBookModel(Set<Book> books) {
        if (books.isEmpty())
            return Collections.emptySet();

        Set<BookModel> bookModels = new HashSet<>();
        for(Book book: books) {
            BookModel bookModel = new BookModel();
            bookModel.setName(book.getName());
            bookModels.add(bookModel);
        }

        return bookModels;
    }

}
