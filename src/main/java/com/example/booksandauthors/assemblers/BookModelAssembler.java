package com.example.booksandauthors.assemblers;

import com.example.booksandauthors.controllers.BookController;
import com.example.booksandauthors.entities.Author;
import com.example.booksandauthors.entities.Book;
import com.example.booksandauthors.models.AuthorModel;
import com.example.booksandauthors.models.BookModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BookModelAssembler extends RepresentationModelAssemblerSupport<Book, BookModel> {

    public BookModelAssembler() {
        super(BookController.class, BookModel.class);
    }


    @Override
    public BookModel toModel(Book entity) {
        BookModel bookModel = instantiateModel(entity);
        bookModel.setName(entity.getName());
        bookModel.setAuthors(toAuthorModel(entity.getAuthors()));
        return bookModel;
    }

    private Set<AuthorModel> toAuthorModel(Set<Author> authors) {
        if (authors.isEmpty())
            return Collections.emptySet();

        Set<AuthorModel> authorModels = new HashSet<>();
        for(Author author: authors) {
            AuthorModel authorModel = new AuthorModel();
            authorModel.setFirstName(author.getFirstName());
            authorModel.setLastName(author.getLastName());
            authorModels.add(authorModel);
        }

        return authorModels;
    }
}
