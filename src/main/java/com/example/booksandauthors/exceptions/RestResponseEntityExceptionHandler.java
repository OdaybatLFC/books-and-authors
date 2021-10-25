package com.example.booksandauthors.exceptions;

import org.hibernate.PropertyValueException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NoSuchElementException.class)
    protected ResponseEntity<Object> handleElementNotFound(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, "Server can't find the requested resource",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {NullPointerException.class, SQLException.class, PropertyValueException.class})
    protected ResponseEntity<Object> handleBadRequests(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, "Malformed request syntax",
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
