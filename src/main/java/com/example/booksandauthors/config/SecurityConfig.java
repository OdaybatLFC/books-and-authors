package com.example.booksandauthors.config;

import com.example.booksandauthors.entities.Author;
import com.example.booksandauthors.services.AuthorService;
import com.example.booksandauthors.services.AuthorUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

import static com.example.booksandauthors.config.UserPermission.*;
import static com.example.booksandauthors.config.UserRole.ADMIN;
import static com.example.booksandauthors.config.UserRole.AUTHOR;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

//    @Autowired
//    private AuthorUserDetailsService userDetailsService;


    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index", "/css/*", "/js/*").permitAll()
//                .antMatchers(HttpMethod.DELETE,"/api/authors/**").hasAuthority(AUTHOR_REMOVE.name())
//                .antMatchers(HttpMethod.POST,"/api/authors").hasAuthority(AUTHOR_ADD.name())
//                .antMatchers(HttpMethod.PATCH,"/api/authors/*").hasAuthority(AUTHOR_UPDATE.name())
//                .antMatchers(HttpMethod.GET,"/api/authors/**").hasAuthority(AUTHOR_READ.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails aleks = User.builder().username("aleks")
                .password(passwordEncoder.encode("123"))
//                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails sam = User.builder().username("sam")
                .password(passwordEncoder.encode("456"))
//                .roles(ADMIN.name())
                .authorities(AUTHOR.getGrantedAuthorities())
                .build();

//        List<UserDetails> users = new ArrayList<>();
//        users.add(aleks);
//        for(Author author: authorService.getAuthors()) {
//            AuthorUserDetails userDetails = new AuthorUserDetails(author);
//            users.add(userDetails);
//        }

        return new InMemoryUserDetailsManager(aleks, sam);
    }
}
