package com.example.booksandauthors.config;

public enum UserPermission {
    BOOK_ADD("book:add"),
    AUTHOR_ADD("author:add"),
    BOOK_REMOVE("book:remove"),
    AUTHOR_UPDATE("author:update"),
    AUTHOR_REMOVE("author:remove"),
    AUTHOR_READ("author:read"),
    BOOK_READ("book:read");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
