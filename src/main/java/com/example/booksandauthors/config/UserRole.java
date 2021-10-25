package com.example.booksandauthors.config;
import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.booksandauthors.config.UserPermission.*;

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
        Set<SimpleGrantedAuthority> authorities = getPermissions().stream()
                .map(permissions -> new SimpleGrantedAuthority((permissions.getPermission())))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
