INSERT INTO author (id, username, password, role, first_name, last_name) VALUES (1, 'emily', 'emily123', 'AUTHOR', 'Emily', 'Dickson');
INSERT INTO author (id, username, password, role, first_name, last_name) VALUES (2, 'arthur', 'arthur123', 'AUTHOR', 'Arthur', 'Doyle');
INSERT INTO author (id, username, password, role, first_name, last_name) VALUES (3, 'kathy', 'kathy123', 'AUTHOR', 'Kathy', 'Sierra');
INSERT INTO author (id, username, password, role, first_name, last_name) VALUES (4, 'bert', 'bert123', 'AUTHOR', 'Bert', 'Bates');
INSERT INTO author (id, username, password, role, first_name, last_name) VALUES (5, 'joanna', 'jk123', 'AUTHOR', 'J.K.', 'Rowling');

INSERT INTO book (isbn, name) VALUES (6, 'Poems');
INSERT INTO book (isbn, name) VALUES (7, 'Sherlock Holmes');
INSERT INTO book (isbn, name) VALUES (8, 'The Napoleonic Tales');
INSERT INTO book (isbn, name) VALUES (9, 'Harry Potter and the philosopher''s stone');
INSERT INTO book (isbn, name) VALUES (10, 'Head First Java');
INSERT INTO book (isbn, name) VALUES (11, 'Head First Design Patterns');

INSERT INTO author_book (id, isbn) VALUES (1, 6);
INSERT INTO author_book (id, isbn) VALUES (2, 7);
INSERT INTO author_book (id, isbn) VALUES (2, 8);
INSERT INTO author_book (id, isbn) VALUES (3, 10);
INSERT INTO author_book (id, isbn) VALUES (4, 10);
INSERT INTO author_book (id, isbn) VALUES (5, 9);
INSERT INTO author_book (id, isbn) VALUES (3, 11);

