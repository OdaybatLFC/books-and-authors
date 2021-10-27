package com.example.booksandauthors.security;
import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.booksandauthors.security.UserPermission.*;

public enum UserRole {
    AUTHOR(Sets.newHashSet(AUTHOR_READ, BOOK_READ,
                           BOOK_REMOVE, AUTHOR_UPDATE, BOOK_ADD)),
    ADMIN(Sets.newHashSet(AUTHOR_READ, BOOK_READ, AUTHOR_REMOVE, AUTHOR_ADD, BOOK_REMOVE, BOOK_ADD,AUTHOR_UPDATE)
);

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        //        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return getPermissions().stream()
                .map(permissions1 -> new SimpleGrantedAuthority((permissions1.getPermission())))
                .collect(Collectors.toSet());
    }
}
