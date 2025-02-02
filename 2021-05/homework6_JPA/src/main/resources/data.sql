DELETE FROM authors;
DELETE FROM books;
DELETE FROM genres;
DELETE FROM comments;
ALTER SEQUENCE book_id_seq RESTART WITH 1;
ALTER SEQUENCE author_id_seq RESTART WITH 1;
ALTER SEQUENCE genre_id_seq RESTART WITH 1;
ALTER SEQUENCE comment_id_seq RESTART WITH 1;

insert into authors (name) values ('Ray Bradbury'),
                                  ('Robert Heinlein');

insert into genres (name) values ('science fiction'),
                                 ('novel');

insert into books (title, author_id, genre_id) values ('dandelion wine', 1, 2),
                                                      ('door into summer', 2, 1);

insert into comments (book_id, text, user_name) values (1, 'comment 1', 'Frank'),
                                                      (2, 'good book', 'Gregory');
